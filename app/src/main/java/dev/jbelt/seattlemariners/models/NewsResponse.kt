package dev.jbelt.seattlemariners.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(
    @Json(name = "header")
    val header: String? = null,
    @Json(name = "link")
    val link: NewsLink? = null,
    @Json(name = "articles")
    val articles: List<Article>? = null
)

@JsonClass(generateAdapter = true)
data class NewsLink(
    @Json(name = "language")
    val language: String? = null,
    @Json(name = "rel")
    val rel: List<String>? = null,
    @Json(name = "href")
    val href: String? = null,
    @Json(name = "text")
    val text: String? = null,
    @Json(name = "shortText")
    val shortText: String? = null,
    @Json(name = "isExternal")
    val isExternal: Boolean? = null,
    @Json(name = "isPremium")
    val isPremium: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class Article(
    @Json(name = "id")
    val id: Long? = null,
    @Json(name = "nowId")
    val nowId: String? = null,
    @Json(name = "contentKey")
    val contentKey: String? = null,
    @Json(name = "dataSourceIdentifier")
    val dataSourceIdentifier: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "headline")
    val headline: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "lastModified")
    val lastModified: String? = null,
    @Json(name = "published")
    val published: String? = null,
    @Json(name = "images")
    val images: List<ArticleImage>? = null,
    @Json(name = "video")
    val video: List<ArticleVideo>? = null,
    @Json(name = "categories")
    val categories: List<Category>? = null,
    @Json(name = "premium")
    val premium: Boolean? = null,
    @Json(name = "links")
    val links: ArticleLinks? = null,
    @Json(name = "byline")
    val byline: String? = null
)

@JsonClass(generateAdapter = true)
data class ArticleImage(
    @Json(name = "dataSourceIdentifier")
    val dataSourceIdentifier: String? = null,
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "caption")
    val caption: String? = null,
    @Json(name = "alt")
    val alt: String? = null,
    @Json(name = "credit")
    val credit: String? = null,
    @Json(name = "height")
    val height: Int? = null,
    @Json(name = "width")
    val width: Int? = null,
    @Json(name = "url")
    val url: String? = null
)

@JsonClass(generateAdapter = true)
data class Category(
    @Json(name = "id")
    val id: Long? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "uid")
    val uid: String? = null,
    @Json(name = "guid")
    val guid: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "sportId")
    val sportId: Int? = null,
    @Json(name = "leagueId")
    val leagueId: Int? = null,
    @Json(name = "league")
    val league: LeagueInfo? = null,
    @Json(name = "teamId")
    val teamId: Int? = null,
    @Json(name = "team")
    val team: TeamInfo? = null,
    @Json(name = "athleteId")
    val athleteId: Long? = null,
    @Json(name = "athlete")
    val athlete: AthleteInfo? = null,
    @Json(name = "topicId")
    val topicId: Int? = null
)

@JsonClass(generateAdapter = true)
data class LeagueInfo(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "abbreviation")
    val abbreviation: String? = null,
    @Json(name = "links")
    val links: CategoryLinks? = null
)

@JsonClass(generateAdapter = true)
data class TeamInfo(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "links")
    val links: CategoryLinks? = null
)

@JsonClass(generateAdapter = true)
data class AthleteInfo(
    @Json(name = "id")
    val id: Long? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "links")
    val links: CategoryLinks? = null
)

@JsonClass(generateAdapter = true)
data class CategoryLinks(
    @Json(name = "web")
    val web: WebLinks? = null,
    @Json(name = "mobile")
    val mobile: MobileLinks? = null
)

@JsonClass(generateAdapter = true)
data class WebLinks(
    @Json(name = "leagues")
    val leagues: LinkHref? = null,
    @Json(name = "teams")
    val teams: LinkHref? = null,
    @Json(name = "athletes")
    val athletes: LinkHref? = null
)

@JsonClass(generateAdapter = true)
data class MobileLinks(
    @Json(name = "leagues")
    val leagues: LinkHref? = null,
    @Json(name = "teams")
    val teams: LinkHref? = null,
    @Json(name = "athletes")
    val athletes: LinkHref? = null
)

@JsonClass(generateAdapter = true)
data class LinkHref(
    @Json(name = "href")
    val href: String? = null
)

@JsonClass(generateAdapter = true)
data class ArticleLinks(
    @Json(name = "web")
    val web: WebArticleLinks? = null,
    @Json(name = "mobile")
    val mobile: MobileArticleLink? = null,
    @Json(name = "api")
    val api: ApiLinks? = null,
    @Json(name = "app")
    val app: AppLinks? = null,
    @Json(name = "sportscenter")
    val sportscenter: LinkHref? = null
)

@JsonClass(generateAdapter = true)
data class WebArticleLinks(
    @Json(name = "href")
    val href: String? = null,
    @Json(name = "self")
    val self: SelfLink? = null
)

@JsonClass(generateAdapter = true)
data class SelfLink(
    @Json(name = "href")
    val href: String? = null,
    @Json(name = "dsi")
    val dsi: LinkHref? = null
)

@JsonClass(generateAdapter = true)
data class MobileArticleLink(
    @Json(name = "href")
    val href: String? = null
)

@JsonClass(generateAdapter = true)
data class ApiLinks(
    @Json(name = "self")
    val self: LinkHref? = null,
    @Json(name = "artwork")
    val artwork: LinkHref? = null
)

@JsonClass(generateAdapter = true)
data class AppLinks(
    @Json(name = "sportscenter")
    val sportscenter: LinkHref? = null
)

@JsonClass(generateAdapter = true)
data class ArticleVideo(
    @Json(name = "id")
    val id: Long? = null,
    @Json(name = "headline")
    val headline: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "duration")
    val duration: Int? = null,
    @Json(name = "thumbnail")
    val thumbnail: String? = null,
    @Json(name = "links")
    val links: ArticleVideoLinks? = null
)

@JsonClass(generateAdapter = true)
data class ArticleVideoLinks(
    @Json(name = "source")
    val source: ArticleVideoSourceLink? = null,
    @Json(name = "mobile")
    val mobile: ArticleVideoMobileLink? = null
)

@JsonClass(generateAdapter = true)
data class ArticleVideoSourceLink(
    @Json(name = "href")
    val href: String? = null,
    @Json(name = "full")
    val full: LinkHref? = null,
    @Json(name = "HLS")
    val hls: ArticleVideoHLSLink? = null
)

@JsonClass(generateAdapter = true)
data class ArticleVideoMobileLink(
    @Json(name = "source")
    val source: LinkHref? = null
)

@JsonClass(generateAdapter = true)
data class ArticleVideoHLSLink(
    @Json(name = "href")
    val href: String? = null
)

