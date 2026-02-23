package dev.jbelt.seattlemariners.repo

import android.util.Log
import dev.jbelt.seattlemariners.BuildConfig
import dev.jbelt.seattlemariners.models.EventsResponse
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "EventsRepository"

@Singleton
class EventsRepository @Inject constructor(
    private val eventsService: EventsService
) {

    /**
     * Fetch all Seattle Mariners events from Ticketmaster API
     * @return Result containing list of all events or error
     */
    suspend fun getMarinersEvents(): Result<List<EventsResponse>> {
        return try {
            Log.d(TAG, "Fetching Seattle Mariners events from Ticketmaster API")
            val searchResponse = eventsService.getEvents(
                attractionId = BuildConfig.TICKETMASTER_ATTRACTION_ID,
                apiKey = BuildConfig.TICKETMASTER_API_KEY,
                sort = "date,asc"
            )

            // Extract all events from the embedded events list
            val events = searchResponse.embedded?.events ?: emptyList()

            if (events.isNotEmpty()) {
                Log.d(TAG, "Successfully fetched ${events.size} events")
                Result.success(events)
            } else {
                Log.d(TAG, "No events found in response")
                Result.failure(Exception("No events found for Seattle Mariners"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching events: ${e.message}", e)
            Result.failure(e)
        }
    }

    suspend fun getUpcomingEvents(): Result<List<EventsResponse>> {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", java.util.Locale.US)
        dateFormat.timeZone = java.util.TimeZone.getTimeZone("UTC")
        val today = dateFormat.format(Date())
        val threeDaysFromNow = dateFormat.format(Date(System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000))

        return try {
            Log.d(TAG, "Fetching Seattle Mariners events from $today to $threeDaysFromNow")
            val searchResponse = eventsService.getUpcomingEvents(
                attractionId = BuildConfig.TICKETMASTER_ATTRACTION_ID,
                apiKey = BuildConfig.TICKETMASTER_API_KEY,
                startDateTime = today,
                endDateTime = threeDaysFromNow
            )

            // Extract all events from the embedded events list
            val events = searchResponse.embedded?.events ?: emptyList()
            if (events.isNotEmpty()) {
                Log.d(TAG, "Successfully fetched ${events.size} events")
                Result.success(events)
            } else {
                Log.d(TAG, "No events found in response")
                Result.failure(Exception("No events found for Seattle Mariners"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching events: ${e.message}", e)
            Result.failure(e)
        }
    }
}

