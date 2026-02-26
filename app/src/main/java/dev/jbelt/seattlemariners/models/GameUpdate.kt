package dev.jbelt.seattlemariners.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameUpdate(
    @Json(name = "timeStamp")
    val timeStamp: String,
    @Json(name = "gamePk")
    val gamePk: String,
    @Json(name = "updateId")
    val updateId: String,
    @Json(name = "wait")
    val wait: Int,
    @Json(name = "logicalEvents")
    val logicalEvents: List<String>,
    @Json(name = "gameEvents")
    val gameEvents: List<String>,
    @Json(name = "changeEvent")
    val changeEvent: ChangeEvent,
    @Json(name = "currentPlay")
    val currentPlay: CurrentPlayData? = null
) {
    /**
     * Parse the timestamp in format "yyyyMMdd_HHmmss" to a readable string
     */
    fun getParsedTimestamp(): String {
        return try {
            if (timeStamp.length >= 15) {
                val date = timeStamp.substring(0, 8)
                val time = timeStamp.substring(9, 15)
                val year = date.substring(0, 4)
                val month = date.substring(4, 6)
                val day = date.substring(6, 8)
                val hour = time.substring(0, 2)
                val minute = time.substring(2, 4)
                val second = time.substring(4, 6)
                "$month/$day/$year $hour:$minute:$second"
            } else {
                timeStamp
            }
        } catch (e: Exception) {
            timeStamp
        }
    }
}

@JsonClass(generateAdapter = true)
data class ChangeEvent(
    @Json(name = "type")
    val type: String
)

