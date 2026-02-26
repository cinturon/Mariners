package dev.jbelt.seattlemariners.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class LiveFeedResponse(
    @Json(name = "liveData")
    val liveData: LiveData?
)

@JsonClass(generateAdapter = true)
data class LiveData(
    @Json(name = "plays")
    val plays: Plays? = null
)

@JsonClass(generateAdapter = true)
data class Plays(
    @Json(name = "currentPlay")
    val currentPlay: CurrentPlay? = null
)

@JsonClass(generateAdapter = true)
data class CurrentPlay(
    @Json(name = "result")
    val result: Result? = null,
    @Json(name = "about")
    val about: About? = null,
    @Json(name = "count")
    val count: Count? = null,
    @Json(name = "matchup")
    val matchup: Matchup? = null,
    @Json(name = "pitchIndex")
    val pitchIndex: List<Int>? = null,
    @Json(name = "actionIndex")
    val actionIndex: List<Int>? = null,
    @Json(name = "runnerIndex")
    val runnerIndex: List<Int>? = null,
    @Json(name = "runners")
    val runners: List<Runner>? = null,
    @Json(name = "playEvents")
    val playEvents: List<PlayEvent>? = null,
    @Json(name = "playEndTime")
    val playEndTime: String? = null,
    @Json(name = "atBatIndex")
    val atBatIndex: Int? = null
)

@JsonClass(generateAdapter = true)
data class Result(
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
    val isOut: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class About(
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
    val isComplete: Boolean? = null,
    @Json(name = "isScoringPlay")
    val isScoringPlay: Boolean? = null,
    @Json(name = "hasReview")
    val hasReview: Boolean? = null,
    @Json(name = "hasOut")
    val hasOut: Boolean? = null,
    @Json(name = "captivatingIndex")
    val captivatingIndex: Int? = null
)

@JsonClass(generateAdapter = true)
data class Count(
    @Json(name = "balls")
    val balls: Int? = null,
    @Json(name = "strikes")
    val strikes: Int? = null,
    @Json(name = "outs")
    val outs: Int? = null
)

@JsonClass(generateAdapter = true)
data class Matchup(
    @Json(name = "batter")
    val batter: Player? = null,
    @Json(name = "batSide")
    val batSide: CodeDescription? = null,
    @Json(name = "pitcher")
    val pitcher: Player? = null,
    @Json(name = "pitchHand")
    val pitchHand: CodeDescription? = null,
    @Json(name = "batterHotColdZones")
    val batterHotColdZones: List<Any>? = null,
    @Json(name = "pitcherHotColdZones")
    val pitcherHotColdZones: List<Any>? = null,
    @Json(name = "splits")
    val splits: Splits? = null
)

@JsonClass(generateAdapter = true)
data class Player(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "fullName")
    val fullName: String? = null,
    @Json(name = "link")
    val link: String? = null
)

@JsonClass(generateAdapter = true)
data class CodeDescription(
    @Json(name = "code")
    val code: String? = null,
    @Json(name = "description")
    val description: String? = null
)

@JsonClass(generateAdapter = true)
data class Splits(
    @Json(name = "batter")
    val batter: String? = null,
    @Json(name = "pitcher")
    val pitcher: String? = null,
    @Json(name = "menOnBase")
    val menOnBase: String? = null
)

@JsonClass(generateAdapter = true)
data class Runner(
    @Json(name = "movement")
    val movement: Movement? = null,
    @Json(name = "details")
    val details: RunnerDetails? = null,
    @Json(name = "credits")
    val credits: List<Credit>? = null
)

@JsonClass(generateAdapter = true)
data class Movement(
    @Json(name = "originBase")
    val originBase: String? = null,
    @Json(name = "start")
    val start: String? = null,
    @Json(name = "end")
    val end: String? = null,
    @Json(name = "outBase")
    val outBase: String? = null,
    @Json(name = "isOut")
    val isOut: Boolean? = null,
    @Json(name = "outNumber")
    val outNumber: Int? = null
)

@JsonClass(generateAdapter = true)
data class RunnerDetails(
    @Json(name = "event")
    val event: String? = null,
    @Json(name = "eventType")
    val eventType: String? = null,
    @Json(name = "movementReason")
    val movementReason: String? = null,
    @Json(name = "runner")
    val runner: Player? = null,
    @Json(name = "responsiblePitcher")
    val responsiblePitcher: Player? = null,
    @Json(name = "isScoringEvent")
    val isScoringEvent: Boolean? = null,
    @Json(name = "rbi")
    val rbi: Boolean? = null,
    @Json(name = "earned")
    val earned: Boolean? = null,
    @Json(name = "teamUnearned")
    val teamUnearned: Boolean? = null,
    @Json(name = "playIndex")
    val playIndex: Int? = null
)

@JsonClass(generateAdapter = true)
data class Credit(
    @Json(name = "player")
    val player: PlayerLink? = null,
    @Json(name = "position")
    val position: FieldPosition? = null,
    @Json(name = "credit")
    val credit: String? = null
)

@JsonClass(generateAdapter = true)
data class PlayerLink(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "link")
    val link: String? = null
)

@JsonClass(generateAdapter = true)
data class FieldPosition(
    @Json(name = "code")
    val code: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "abbreviation")
    val abbreviation: String? = null
)

@JsonClass(generateAdapter = true)
data class PlayEvent(
    @Json(name = "details")
    val details: PlayEventDetails? = null,
    @Json(name = "count")
    val count: Count? = null,
    @Json(name = "pitchData")
    val pitchData: PitchData? = null,
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
    val isPitch: Boolean? = null,
    @Json(name = "type")
    val type: String? = null
)

@JsonClass(generateAdapter = true)
data class PlayEventDetails(
    @Json(name = "call")
    val call: CodeDescription? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "code")
    val code: String? = null,
    @Json(name = "ballColor")
    val ballColor: String? = null,
    @Json(name = "trailColor")
    val trailColor: String? = null,
    @Json(name = "isInPlay")
    val isInPlay: Boolean? = null,
    @Json(name = "isStrike")
    val isStrike: Boolean? = null,
    @Json(name = "isBall")
    val isBall: Boolean? = null,
    @Json(name = "type")
    val type: CodeDescription? = null,
    @Json(name = "isOut")
    val isOut: Boolean? = null,
    @Json(name = "hasReview")
    val hasReview: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class PitchData(
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
    val coordinates: Coordinates? = null,
    @Json(name = "breaks")
    val breaks: Breaks? = null,
    @Json(name = "zone")
    val zone: Int? = null,
    @Json(name = "plateTime")
    val plateTime: Double? = null,
    @Json(name = "extension")
    val extension: Double? = null
)

@JsonClass(generateAdapter = true)
data class Coordinates(
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

@JsonClass(generateAdapter = true)
data class Breaks(
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

