package dev.jbelt.seattlemariners.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Wrapper response for Ticketmaster API events search
 * The API returns a list of events wrapped in an _embedded object
 */
@JsonClass(generateAdapter = true)
data class EventsSearchResponse(
    @Json(name = "_embedded")
    val embedded: EventsEmbedded? = null
)

@JsonClass(generateAdapter = true)
data class EventsEmbedded(
    @Json(name = "events")
    val events: List<EventsResponse>? = null
)

