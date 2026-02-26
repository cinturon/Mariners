package dev.jbelt.seattlemariners.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Main data class for current play information in WebSocket updates
 * Represents the complete state of the current at-bat and play
 */
@JsonClass(generateAdapter = true)
data class CurrentPlayData(
    @Json(name = "result")
    val result: PlayResult,
    @Json(name = "about")
    val about: PlayAbout,
    @Json(name = "count")
    val count: PlayCount,
    @Json(name = "matchup")
    val matchup: PlayMatchup? = null,
    @Json(name = "pitchIndex")
    val pitchIndex: List<Int> = emptyList(),
    @Json(name = "actionIndex")
    val actionIndex: List<Int> = emptyList(),
    @Json(name = "runnerIndex")
    val runnerIndex: List<Int> = emptyList(),
    @Json(name = "runners")
    val runners: List<RunnerData> = emptyList(),
    @Json(name = "playEvents")
    val playEvents: List<PlayEventData> = emptyList(),
    @Json(name = "playEndTime")
    val playEndTime: String? = null,
    @Json(name = "atBatIndex")
    val atBatIndex: Int? = null
)

/**
 * Represents the result of an at-bat or play
 */
@JsonClass(generateAdapter = true)
data class PlayResult(
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "event")
    val event: String? = null,
    @Json(name = "eventType")
    val eventType: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "rbi")
    val rbi: Int? = null,
    @Json(name = "awayScore")
    val awayScore: Int? = null,
    @Json(name = "homeScore")
    val homeScore: Int? = null,
    @Json(name = "isOut")
    val isOut: Boolean = false
)

/**
 * Represents timing and context information about the play
 */
@JsonClass(generateAdapter = true)
data class PlayAbout(
    @Json(name = "atBatIndex")
    val atBatIndex: Int? = null,
    @Json(name = "halfInning")
    val halfInning: String? = null,
    @Json(name = "isTopInning")
    val isTopInning: Boolean? = null,
    @Json(name = "inning")
    val inning: Int? = null,
    @Json(name = "startTime")
    val startTime: String? = null,
    @Json(name = "endTime")
    val endTime: String? = null,
    @Json(name = "isComplete")
    val isComplete: Boolean = false,
    @Json(name = "isScoringPlay")
    val isScoringPlay: Boolean = false,
    @Json(name = "hasReview")
    val hasReview: Boolean = false,
    @Json(name = "hasOut")
    val hasOut: Boolean = false,
    @Json(name = "captivatingIndex")
    val captivatingIndex: Int? = null
)

/**
 * Represents the current count (balls, strikes, outs)
 */
@JsonClass(generateAdapter = true)
data class PlayCount(
    @Json(name = "balls")
    val balls: Int? = null,
    @Json(name = "strikes")
    val strikes: Int? = null,
    @Json(name = "outs")
    val outs: Int? = null
)

/**
 * Represents batter vs pitcher matchup information
 */
@JsonClass(generateAdapter = true)
data class PlayMatchup(
    @Json(name = "batter")
    val batter: PlayerData? = null,
    @Json(name = "batSide")
    val batSide: CodeDescriptionData? = null,
    @Json(name = "pitcher")
    val pitcher: PlayerData? = null,
    @Json(name = "pitchHand")
    val pitchHand: CodeDescriptionData? = null,
    @Json(name = "batterHotColdZones")
    val batterHotColdZones: List<Any> = emptyList(),
    @Json(name = "pitcherHotColdZones")
    val pitcherHotColdZones: List<Any> = emptyList(),
    @Json(name = "splits")
    val splits: SplitsData? = null
)

/**
 * Represents a player (batter, pitcher, etc.)
 */
@JsonClass(generateAdapter = true)
data class PlayerData(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "fullName")
    val fullName: String? = null,
    @Json(name = "link")
    val link: String? = null
)

/**
 * Represents a code and description pair
 */
@JsonClass(generateAdapter = true)
data class CodeDescriptionData(
    @Json(name = "code")
    val code: String? = null,
    @Json(name = "description")
    val description: String? = null
)

/**
 * Represents split statistics (batter vs pitcher type, bases state)
 */
@JsonClass(generateAdapter = true)
data class SplitsData(
    @Json(name = "batter")
    val batter: String? = null,
    @Json(name = "pitcher")
    val pitcher: String? = null,
    @Json(name = "menOnBase")
    val menOnBase: String? = null
)

/**
 * Represents a runner on base and their movement
 */
@JsonClass(generateAdapter = true)
data class RunnerData(
    @Json(name = "movement")
    val movement: RunnerMovement? = null,
    @Json(name = "details")
    val details: RunnerDetailsData? = null,
    @Json(name = "credits")
    val credits: List<CreditData> = emptyList()
)

/**
 * Represents runner movement information
 */
@JsonClass(generateAdapter = true)
data class RunnerMovement(
    @Json(name = "originBase")
    val originBase: String? = null,
    @Json(name = "start")
    val start: String? = null,
    @Json(name = "end")
    val end: String? = null,
    @Json(name = "outBase")
    val outBase: String? = null,
    @Json(name = "isOut")
    val isOut: Boolean = false,
    @Json(name = "outNumber")
    val outNumber: Int? = null
)

/**
 * Represents details about a runner's action
 */
@JsonClass(generateAdapter = true)
data class RunnerDetailsData(
    @Json(name = "event")
    val event: String? = null,
    @Json(name = "eventType")
    val eventType: String? = null,
    @Json(name = "movementReason")
    val movementReason: String? = null,
    @Json(name = "runner")
    val runner: PlayerData? = null,
    @Json(name = "responsiblePitcher")
    val responsiblePitcher: PlayerData? = null,
    @Json(name = "isScoringEvent")
    val isScoringEvent: Boolean = false,
    @Json(name = "rbi")
    val rbi: Boolean = false,
    @Json(name = "earned")
    val earned: Boolean = false,
    @Json(name = "teamUnearned")
    val teamUnearned: Boolean = false,
    @Json(name = "playIndex")
    val playIndex: Int? = null
)

/**
 * Represents credit for an out (fielder who made the play)
 */
@JsonClass(generateAdapter = true)
data class CreditData(
    @Json(name = "player")
    val player: PlayerLinkData? = null,
    @Json(name = "position")
    val position: PositionData? = null,
    @Json(name = "credit")
    val credit: String? = null
)

/**
 * Represents a player link (ID and API link)
 */
@JsonClass(generateAdapter = true)
data class PlayerLinkData(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "link")
    val link: String? = null
)

/**
 * Represents a fielding position
 */
@JsonClass(generateAdapter = true)
data class PositionData(
    @Json(name = "code")
    val code: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "abbreviation")
    val abbreviation: String? = null
)

/**
 * Represents a single pitch or play event
 */
@JsonClass(generateAdapter = true)
data class PlayEventData(
    @Json(name = "details")
    val details: PlayEventDetailsData? = null,
    @Json(name = "count")
    val count: PlayCount? = null,
    @Json(name = "pitchData")
    val pitchData: PitchDataInfo? = null,
    @Json(name = "index")
    val index: Int? = null,
    @Json(name = "playId")
    val playId: String? = null,
    @Json(name = "pitchNumber")
    val pitchNumber: Int? = null,
    @Json(name = "startTime")
    val startTime: String? = null,
    @Json(name = "endTime")
    val endTime: String? = null,
    @Json(name = "isPitch")
    val isPitch: Boolean = false,
    @Json(name = "type")
    val type: String? = null
)

/**
 * Represents details about a pitch or play event
 */
@JsonClass(generateAdapter = true)
data class PlayEventDetailsData(
    @Json(name = "call")
    val call: CodeDescriptionData? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "code")
    val code: String? = null,
    @Json(name = "ballColor")
    val ballColor: String? = null,
    @Json(name = "trailColor")
    val trailColor: String? = null,
    @Json(name = "isInPlay")
    val isInPlay: Boolean = false,
    @Json(name = "isStrike")
    val isStrike: Boolean = false,
    @Json(name = "isBall")
    val isBall: Boolean = false,
    @Json(name = "type")
    val type: CodeDescriptionData? = null,
    @Json(name = "isOut")
    val isOut: Boolean = false,
    @Json(name = "hasReview")
    val hasReview: Boolean = false
)

/**
 * Represents detailed pitch tracking data
 */
@JsonClass(generateAdapter = true)
data class PitchDataInfo(
    @Json(name = "startSpeed")
    val startSpeed: Double? = null,
    @Json(name = "endSpeed")
    val endSpeed: Double? = null,
    @Json(name = "strikeZoneTop")
    val strikeZoneTop: Double? = null,
    @Json(name = "strikeZoneBottom")
    val strikeZoneBottom: Double? = null,
    @Json(name = "strikeZoneWidth")
    val strikeZoneWidth: Double? = null,
    @Json(name = "strikeZoneDepth")
    val strikeZoneDepth: Double? = null,
    @Json(name = "coordinates")
    val coordinates: CoordinatesData? = null,
    @Json(name = "breaks")
    val breaksData: BreaksData? = null,
    @Json(name = "zone")
    val zone: Int? = null,
    @Json(name = "plateTime")
    val plateTime: Double? = null,
    @Json(name = "extension")
    val extension: Double? = null
)

/**
 * Represents pitch coordinates (location, velocity, acceleration)
 */
@JsonClass(generateAdapter = true)
data class CoordinatesData(
    @Json(name = "aY")
    val aY: Double? = null,
    @Json(name = "aZ")
    val aZ: Double? = null,
    @Json(name = "pfxX")
    val pfxX: Double? = null,
    @Json(name = "pfxZ")
    val pfxZ: Double? = null,
    @Json(name = "pX")
    val pX: Double? = null,
    @Json(name = "pZ")
    val pZ: Double? = null,
    @Json(name = "vX0")
    val vX0: Double? = null,
    @Json(name = "vY0")
    val vY0: Double? = null,
    @Json(name = "vZ0")
    val vZ0: Double? = null,
    @Json(name = "x")
    val x: Double? = null,
    @Json(name = "y")
    val y: Double? = null,
    @Json(name = "x0")
    val x0: Double? = null,
    @Json(name = "y0")
    val y0: Double? = null,
    @Json(name = "z0")
    val z0: Double? = null,
    @Json(name = "aX")
    val aX: Double? = null
)

/**
 * Represents pitch break information (movement, spin)
 */
@JsonClass(generateAdapter = true)
data class BreaksData(
    @Json(name = "breakAngle")
    val breakAngle: Double? = null,
    @Json(name = "breakVertical")
    val breakVertical: Double? = null,
    @Json(name = "breakVerticalInduced")
    val breakVerticalInduced: Double? = null,
    @Json(name = "breakHorizontal")
    val breakHorizontal: Double? = null,
    @Json(name = "spinRate")
    val spinRate: Int? = null,
    @Json(name = "spinDirection")
    val spinDirection: Int? = null
)

