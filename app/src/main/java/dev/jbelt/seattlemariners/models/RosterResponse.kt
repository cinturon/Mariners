package dev.jbelt.seattlemariners.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RosterResponse(
    @Json(name = "timestamp")
    val timestamp: String,
    @Json(name = "status")
    val status: String,
    @Json(name = "season")
    val season: Season,
    @Json(name = "athletes")
    val athletes: List<PositionGroup>
)

@JsonClass(generateAdapter = true)
data class Season(
    @Json(name = "year")
    val year: Int,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "type")
    val type: Int,
    @Json(name = "name")
    val name: String
)

@JsonClass(generateAdapter = true)
data class PositionGroup(
    @Json(name = "position")
    val position: String,
    @Json(name = "items")
    val items: List<Athlete>
)

@JsonClass(generateAdapter = true)
data class Athlete(
    @Json(name = "id")
    val id: String,
    @Json(name = "uid")
    val uid: String,
    @Json(name = "guid")
    val guid: String,
    @Json(name = "alternateIds")
    val alternateIds: AlternateIds? = null,
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "lastName")
    val lastName: String,
    @Json(name = "fullName")
    val fullName: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "shortName")
    val shortName: String,
    @Json(name = "weight")
    val weight: Double? = null,
    @Json(name = "displayWeight")
    val displayWeight: String? = null,
    @Json(name = "height")
    val height: Double? = null,
    @Json(name = "displayHeight")
    val displayHeight: String? = null,
    @Json(name = "age")
    val age: Int? = null,
    @Json(name = "dateOfBirth")
    val dateOfBirth: String? = null,
    @Json(name = "debutYear")
    val debutYear: Int? = null,
    @Json(name = "links")
    val links: List<Link>? = null,
    @Json(name = "birthPlace")
    val birthPlace: BirthPlace? = null,
    @Json(name = "slug")
    val slug: String,
    @Json(name = "headshot")
    val headshot: Image? = null,
    @Json(name = "jersey")
    val jersey: String? = null,
    @Json(name = "position")
    val position: Position? = null,
    @Json(name = "injuries")
    val injuries: List<Any> = emptyList(),
    @Json(name = "contracts")
    val contracts: List<Any> = emptyList(),
    @Json(name = "experience")
    val experience: Experience? = null,
    @Json(name = "status")
    val status: Status? = null,
    @Json(name = "bats")
    val bats: HandThrow? = null,
    @Json(name = "throws")
    val throws: HandThrow? = null,
    @Json(name = "college")
    val college: College? = null
)

@JsonClass(generateAdapter = true)
data class AlternateIds(
    @Json(name = "sdr")
    val sdr: String? = null
)

@JsonClass(generateAdapter = true)
data class Link(
    @Json(name = "language")
    val language: String,
    @Json(name = "rel")
    val rel: List<String>,
    @Json(name = "href")
    val href: String,
    @Json(name = "text")
    val text: String,
    @Json(name = "shortText")
    val shortText: String,
    @Json(name = "isExternal")
    val isExternal: Boolean,
    @Json(name = "isPremium")
    val isPremium: Boolean
)

@JsonClass(generateAdapter = true)
data class BirthPlace(
    @Json(name = "city")
    val city: String? = null,
    @Json(name = "state")
    val state: String? = null,
    @Json(name = "country")
    val country: String? = null,
    @Json(name = "displayText")
    val displayText: String
)

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "href")
    val href: String,
    @Json(name = "alt")
    val alt: String
)

@JsonClass(generateAdapter = true)
data class Position(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "abbreviation")
    val abbreviation: String,
    @Json(name = "leaf")
    val leaf: Boolean,
    @Json(name = "parent")
    val parent: PositionParent? = null
)

@JsonClass(generateAdapter = true)
data class PositionParent(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "abbreviation")
    val abbreviation: String,
    @Json(name = "leaf")
    val leaf: Boolean
)

@JsonClass(generateAdapter = true)
data class Experience(
    @Json(name = "years")
    val years: Int
)

@JsonClass(generateAdapter = true)
data class Status(
    @Json(name = "id")
    val id: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "abbreviation")
    val abbreviation: String
)

@JsonClass(generateAdapter = true)
data class HandThrow(
    @Json(name = "type")
    val type: String,
    @Json(name = "abbreviation")
    val abbreviation: String,
    @Json(name = "displayValue")
    val displayValue: String
)

@JsonClass(generateAdapter = true)
data class College(
    @Json(name = "id")
    val id: String,
    @Json(name = "guid")
    val guid: String,
    @Json(name = "mascot")
    val mascot: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "shortName")
    val shortName: String,
    @Json(name = "abbrev")
    val abbrev: String,
    @Json(name = "logos")
    val logos: List<Logo>
)

@JsonClass(generateAdapter = true)
data class Logo(
    @Json(name = "href")
    val href: String,
    @Json(name = "width")
    val width: Int,
    @Json(name = "height")
    val height: Int,
    @Json(name = "alt")
    val alt: String,
    @Json(name = "rel")
    val rel: List<String>,
    @Json(name = "lastUpdated")
    val lastUpdated: String
)
