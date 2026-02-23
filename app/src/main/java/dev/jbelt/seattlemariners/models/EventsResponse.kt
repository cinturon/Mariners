package dev.jbelt.seattlemariners.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventsResponse(
    @Json(name = "_embedded")
    val embedded: EmbeddedData? = null,
    @Json(name = "_links")
    val links: ResponseLinks? = null,
    @Json(name = "classifications")
    val classifications: List<Classification>? = null,
    @Json(name = "dates")
    val dates: EventDates? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "images")
    val images: List<EventImage>? = null,
    @Json(name = "locale")
    val locale: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "pleaseNote")
    val pleaseNote: String? = null,
    @Json(name = "priceRanges")
    val priceRanges: List<PriceRange>? = null,
    @Json(name = "promoter")
    val promoter: Promoter? = null,
    @Json(name = "sales")
    val sales: Sales? = null,
    @Json(name = "test")
    val test: Boolean? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "url")
    val url: String? = null
)

@JsonClass(generateAdapter = true)
data class EmbeddedData(
    @Json(name = "venues")
    val venues: List<Venue>? = null,
    @Json(name = "attractions")
    val attractions: List<Attraction>? = null
)

@JsonClass(generateAdapter = true)
data class Venue(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "test")
    val test: Boolean? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "locale")
    val locale: String? = null,
    @Json(name = "postalCode")
    val postalCode: String? = null,
    @Json(name = "timezone")
    val timezone: String? = null,
    @Json(name = "city")
    val city: City? = null,
    @Json(name = "state")
    val state: State? = null,
    @Json(name = "country")
    val country: Country? = null,
    @Json(name = "address")
    val address: Address? = null,
    @Json(name = "location")
    val location: Location? = null,
    @Json(name = "markets")
    val markets: List<Market>? = null,
    @Json(name = "dmas")
    val dmas: List<DMA>? = null,
    @Json(name = "_links")
    val links: VenueLinks? = null
)

@JsonClass(generateAdapter = true)
data class Attraction(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "test")
    val test: Boolean? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "locale")
    val locale: String? = null,
    @Json(name = "images")
    val images: List<AttractionImage>? = null,
    @Json(name = "classifications")
    val classifications: List<Classification>? = null,
    @Json(name = "_links")
    val links: AttractionLinks? = null
)

@JsonClass(generateAdapter = true)
data class City(
    @Json(name = "name")
    val name: String? = null
)

@JsonClass(generateAdapter = true)
data class State(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "stateCode")
    val stateCode: String? = null
)

@JsonClass(generateAdapter = true)
data class Country(
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "countryCode")
    val countryCode: String? = null
)

@JsonClass(generateAdapter = true)
data class Address(
    @Json(name = "line1")
    val line1: String? = null
)

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "longitude")
    val longitude: String? = null,
    @Json(name = "latitude")
    val latitude: String? = null
)

@JsonClass(generateAdapter = true)
data class Market(
    @Json(name = "id")
    val id: String? = null
)

@JsonClass(generateAdapter = true)
data class DMA(
    @Json(name = "id")
    val id: Int? = null
)

@JsonClass(generateAdapter = true)
data class VenueLinks(
    @Json(name = "self")
    val self: EventLink? = null
)

@JsonClass(generateAdapter = true)
data class AttractionImage(
    @Json(name = "ratio")
    val ratio: String? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "width")
    val width: Int? = null,
    @Json(name = "height")
    val height: Int? = null,
    @Json(name = "fallback")
    val fallback: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class AttractionLinks(
    @Json(name = "self")
    val self: EventLink? = null
)

@JsonClass(generateAdapter = true)
data class Classification(
    @Json(name = "primary")
    val primary: Boolean? = null,
    @Json(name = "segment")
    val segment: Segment? = null,
    @Json(name = "genre")
    val genre: Genre? = null,
    @Json(name = "subGenre")
    val subGenre: SubGenre? = null
)

@JsonClass(generateAdapter = true)
data class Segment(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null
)

@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null
)

@JsonClass(generateAdapter = true)
data class SubGenre(
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "name")
    val name: String? = null
)

@JsonClass(generateAdapter = true)
data class ResponseLinks(
    @Json(name = "self")
    val self: EventLink? = null,
    @Json(name = "attractions")
    val attractions: List<EventLink>? = null,
    @Json(name = "venues")
    val venues: List<EventLink>? = null
)

@JsonClass(generateAdapter = true)
data class EventLink(
    @Json(name = "href")
    val href: String? = null
)

@JsonClass(generateAdapter = true)
data class EventDates(
    @Json(name = "start")
    val start: StartDate? = null,
    @Json(name = "timezone")
    val timezone: String? = null,
    @Json(name = "status")
    val status: EventStatus? = null
)

@JsonClass(generateAdapter = true)
data class StartDate(
    @Json(name = "localDate")
    val localDate: String? = null,
    @Json(name = "localTime")
    val localTime: String? = null,
    @Json(name = "dateTime")
    val dateTime: String? = null,
    @Json(name = "dateTBD")
    val dateTBD: Boolean? = null,
    @Json(name = "dateTBA")
    val dateTBA: Boolean? = null,
    @Json(name = "timeTBA")
    val timeTBA: Boolean? = null,
    @Json(name = "noSpecificTime")
    val noSpecificTime: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class EventStatus(
    @Json(name = "code")
    val code: String? = null
)

@JsonClass(generateAdapter = true)
data class EventImage(
    @Json(name = "ratio")
    val ratio: String? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "width")
    val width: Int? = null,
    @Json(name = "height")
    val height: Int? = null,
    @Json(name = "fallback")
    val fallback: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class PriceRange(
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "currency")
    val currency: String? = null,
    @Json(name = "min")
    val min: Double? = null,
    @Json(name = "max")
    val max: Double? = null
)

@JsonClass(generateAdapter = true)
data class Promoter(
    @Json(name = "id")
    val id: String? = null
)

@JsonClass(generateAdapter = true)
data class Sales(
    @Json(name = "public")
    val public: SalesWindow? = null
)

@JsonClass(generateAdapter = true)
data class SalesWindow(
    @Json(name = "startDateTime")
    val startDateTime: String? = null,
    @Json(name = "startTBD")
    val startTBD: Boolean? = null,
    @Json(name = "endDateTime")
    val endDateTime: String? = null
)

