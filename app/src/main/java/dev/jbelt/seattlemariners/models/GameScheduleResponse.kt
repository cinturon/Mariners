package dev.jbelt.seattlemariners.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameScheduleResponse(
    @Json(name = "copyright")
    val copyright: String? = null,
    @Json(name = "totalItems")
    val totalItems: Int? = null,
    @Json(name = "totalEvents")
    val totalEvents: Int? = null,
    @Json(name = "totalGames")
    val totalGames: Int? = null,
    @Json(name = "totalGamesInProgress")
    val totalGamesInProgress: Int? = null,
    @Json(name = "dates")
    val dates: List<GameDate>? = null
)

@JsonClass(generateAdapter = true)
data class GameDate(
    @Json(name = "date")
    val date: String? = null,
    @Json(name = "totalItems")
    val totalItems: Int? = null,
    @Json(name = "totalEvents")
    val totalEvents: Int? = null,
    @Json(name = "totalGames")
    val totalGames: Int? = null,
    @Json(name = "totalGamesInProgress")
    val totalGamesInProgress: Int? = null,
    @Json(name = "games")
    val games: List<Game>? = null,
    @Json(name = "events")
    val events: List<Any>? = null
)

@JsonClass(generateAdapter = true)
data class Game(
    @Json(name = "gamePk")
    val gamePk: Int? = null,
    @Json(name = "gameGuid")
    val gameGuid: String? = null,
    @Json(name = "link")
    val link: String? = null,
    @Json(name = "gameType")
    val gameType: String? = null,
    @Json(name = "season")
    val season: String? = null,
    @Json(name = "gameDate")
    val gameDate: String? = null,
    @Json(name = "officialDate")
    val officialDate: String? = null,
    @Json(name = "status")
    val status: GameStatus? = null,
    @Json(name = "teams")
    val teams: Teams? = null,
    @Json(name = "venue")
    val venue: GameVenue? = null,
    @Json(name = "content")
    val content: GameContent? = null,
    @Json(name = "gameNumber")
    val gameNumber: Int? = null,
    @Json(name = "publicFacing")
    val publicFacing: Boolean? = null,
    @Json(name = "doubleHeader")
    val doubleHeader: String? = null,
    @Json(name = "gamedayType")
    val gamedayType: String? = null,
    @Json(name = "tiebreaker")
    val tiebreaker: String? = null,
    @Json(name = "calendarEventID")
    val calendarEventID: String? = null,
    @Json(name = "seasonDisplay")
    val seasonDisplay: String? = null,
    @Json(name = "dayNight")
    val dayNight: String? = null,
    @Json(name = "scheduledInnings")
    val scheduledInnings: Int? = null,
    @Json(name = "reverseHomeAwayStatus")
    val reverseHomeAwayStatus: Boolean? = null,
    @Json(name = "inningBreakLength")
    val inningBreakLength: Int? = null,
    @Json(name = "gamesInSeries")
    val gamesInSeries: Int? = null,
    @Json(name = "seriesGameNumber")
    val seriesGameNumber: Int? = null,
    @Json(name = "seriesDescription")
    val seriesDescription: String? = null,
    @Json(name = "recordSource")
    val recordSource: String? = null,
    @Json(name = "ifNecessary")
    val ifNecessary: String? = null,
    @Json(name = "ifNecessaryDescription")
    val ifNecessaryDescription: String? = null
)

@JsonClass(generateAdapter = true)
data class GameStatus(
    @Json(name = "abstractGameState")
    val abstractGameState: String? = null,
    @Json(name = "codedGameState")
    val codedGameState: String? = null,
    @Json(name = "detailedState")
    val detailedState: String? = null,
    @Json(name = "statusCode")
    val statusCode: String? = null,
    @Json(name = "startTimeTBD")
    val startTimeTBD: Boolean? = null,
    @Json(name = "abstractGameCode")
    val abstractGameCode: String? = null
)

@JsonClass(generateAdapter = true)
data class Teams(
    @Json(name = "away")
    val away: GameTeamInfo? = null,
    @Json(name = "home")
    val home: GameTeamInfo? = null
)

@JsonClass(generateAdapter = true)
data class GameTeamInfo(
    @Json(name = "team")
    val team: TeamDetails? = null,
    @Json(name = "leagueRecord")
    val leagueRecord: LeagueRecord? = null,
    @Json(name = "score")
    val score: Int? = null,
    @Json(name = "splitSquad")
    val splitSquad: Boolean? = null,
    @Json(name = "seriesNumber")
    val seriesNumber: Int? = null
)

@JsonClass(generateAdapter = true)
data class TeamDetails(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "link")
    val link: String? = null
)

@JsonClass(generateAdapter = true)
data class LeagueRecord(
    @Json(name = "wins")
    val wins: Int? = null,
    @Json(name = "losses")
    val losses: Int? = null,
    @Json(name = "pct")
    val pct: String? = null
)

@JsonClass(generateAdapter = true)
data class GameVenue(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "link")
    val link: String? = null
)

@JsonClass(generateAdapter = true)
data class GameContent(
    @Json(name = "link")
    val link: String? = null
)

