package dev.jbelt.seattlemariners.repo

import android.util.Log
import com.squareup.moshi.Moshi
import dev.jbelt.seattlemariners.models.CurrentPlay
import dev.jbelt.seattlemariners.models.GameUpdate
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.websocket.CloseReason
import io.ktor.websocket.Frame
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import java.io.EOFException
import javax.inject.Inject
import kotlin.math.min

private const val TAG = "LiveGameService"
private const val MAX_RETRY_ATTEMPTS = 5
private const val INITIAL_RETRY_DELAY_MS = 1000L
private const val MAX_RETRY_DELAY_MS = 30000L

class LiveGameService @Inject constructor(
    private val client: HttpClient,
    private val moshi: Moshi,
    private val statsService: StatsService
){
    private var session: DefaultClientWebSocketSession? = null
    private var shouldReconnect = true

    fun initSession(gamePk: Int): Flow<CurrentPlay> = flow {
        var retryAttempt = 0

        Log.d(TAG, "initSession start: gamePk=$gamePk shouldReconnect=$shouldReconnect")

        while (shouldReconnect && retryAttempt < MAX_RETRY_ATTEMPTS) {
            try {
                Log.d(TAG, "Reconnect loop: attempt=$retryAttempt sessionActive=${session != null}")
                if (retryAttempt > 0) {
                    val delayMs = min(INITIAL_RETRY_DELAY_MS * (1 shl (retryAttempt - 1)), MAX_RETRY_DELAY_MS)
                    Log.d(TAG, "Reconnecting in ${delayMs}ms... (Attempt ${retryAttempt + 1}/$MAX_RETRY_ATTEMPTS)")
                    delay(delayMs)
                }

                Log.d(TAG, "Initializing WebSocket session for game: $gamePk")
                session = client.webSocketSession("wss://ws.statsapi.mlb.com/api/v1/game/push/subscribe/gameday/${gamePk}")

                Log.d(TAG, "WebSocket session established: sessionId=${session?.hashCode()}")
                val gameUpdateAdapter = moshi.adapter(GameUpdate::class.java)

                val incoming = session?.incoming
                if (incoming == null) {
                    Log.e(TAG, "Failed to establish WebSocket session - incoming is null")
                    retryAttempt++
                    continue
                }

                retryAttempt = 0
                Log.d(TAG, "Ready to receive frames")

                emitAll(
                    incoming.receiveAsFlow()
                        .filterIsInstance<Frame.Text>()
                        .map { frame ->
                            try {
                                val json = frame.readText()
                                Log.d(TAG, "Received frame length=${json.length}")

                                if (json.isEmpty() || !json.startsWith("{")) {
                                    Log.w(TAG, "Invalid JSON format received: $json")
                                    return@map null
                                }

                                val gameUpdate = gameUpdateAdapter.fromJson(json)
                                if (gameUpdate != null) {
                                    Log.d(TAG, "Parsed GameUpdate: gamePk=${gameUpdate.gamePk} timeStamp=${gameUpdate.timeStamp} updateId=${gameUpdate.updateId}")
                                    try {
                                        Log.d(TAG, "Fetching live feed: gamePk=${gameUpdate.gamePk} timeStamp=${gameUpdate.timeStamp}")
                                        val liveFeedData = statsService.getLiveFeed(gameUpdate.gamePk, gameUpdate.timeStamp)
                                        val currentPlay = liveFeedData.liveData?.plays?.currentPlay
                                        Log.d(TAG, "Live feed fetched: hasCurrentPlay=${currentPlay != null}")
                                        currentPlay
                                    } catch (e: Exception) {
                                        Log.e(TAG, "Error fetching live feed data: ${e.message}", e)
                                        null
                                    }
                                } else {
                                    Log.w(TAG, "Parsed GameUpdate was null from JSON")
                                    null
                                }
                            } catch (e: EOFException) {
                                Log.e(TAG, "EOFException while parsing JSON - connection may have closed", e)
                                null
                            } catch (e: Exception) {
                                Log.e(TAG, "Error parsing JSON: ${e.message}", e)
                                null
                            }
                        }
                        .filterNotNull()
                )

                if (shouldReconnect) {
                    Log.w(TAG, "WebSocket flow ended; scheduling reconnect")
                    retryAttempt = (0 + 1).coerceAtLeast(1)
                    continue
                }

                break

            } catch (e: EOFException) {
                Log.e(TAG, "EOFException in session: ${e.message}", e)
                retryAttempt++
                if (retryAttempt >= MAX_RETRY_ATTEMPTS) {
                    Log.e(TAG, "Max retry attempts reached. Giving up.")
                    break
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error in session: ${e.message}", e)
                retryAttempt++
                if (retryAttempt >= MAX_RETRY_ATTEMPTS) {
                    Log.e(TAG, "Max retry attempts reached. Giving up.")
                    break
                }
            } finally {
                Log.d(TAG, "WebSocket session closing: shouldReconnect=$shouldReconnect")
                closeSession(stopReconnect = false)
            }
        }

        if (retryAttempt >= MAX_RETRY_ATTEMPTS) {
            Log.e(TAG, "Failed to connect after $MAX_RETRY_ATTEMPTS attempts")
        }
    }

    suspend fun closeSession(stopReconnect: Boolean = true) {
        try {
            if (stopReconnect) {
                shouldReconnect = false
            }
            Log.d(TAG, "Closing session: stopReconnect=$stopReconnect sessionActive=${session != null}")
            session?.close(CloseReason(CloseReason.Codes.NORMAL, "User disconnected"))
            session = null
            Log.d(TAG, "Session closed successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Error closing session: ${e.message}", e)
        }
    }

    fun enableReconnect() {
        shouldReconnect = true
        Log.d(TAG, "Reconnect enabled")
    }
}