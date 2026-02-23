package dev.jbelt.seattlemariners.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeadlinesResponse(
    @Json(name = "resultsCount")
    val resultsCount: Int? = null,
    @Json(name = "resultsLimit")
    val resultsLimit: Int? = null,
    @Json(name = "resultsOffset")
    val resultsOffset: Int? = null,
    @Json(name = "headlines")
    val headlines: List<Headline>? = null,
    @Json(name = "breakingNews")
    val breakingNews: List<BreakingNews>? = null,
    @Json(name = "timestamp")
    val timestamp: String? = null,
    @Json(name = "status")
    val status: String? = null
)

@JsonClass(generateAdapter = true)
data class Headline(
    @Json(name = "id")
    val id: Long? = null,
    @Json(name = "nowId")
    val nowId: String? = null,
    @Json(name = "contentKey")
    val contentKey: String? = null,
    @Json(name = "dataSourceIdentifier")
    val dataSourceIdentifier: String? = null,
    @Json(name = "publishedkey")
    val publishedKey: String? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "feedDisplayType")
    val feedDisplayType: String? = null,
    @Json(name = "headline")
    val headline: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "linkText")
    val linkText: String? = null,
    @Json(name = "categorized")
    val categorized: String? = null,
    @Json(name = "originallyPosted")
    val originallyPosted: String? = null,
    @Json(name = "lastModified")
    val lastModified: String? = null,
    @Json(name = "published")
    val published: String? = null,
    @Json(name = "root")
    val root: String? = null,
    @Json(name = "section")
    val section: String? = null,
    @Json(name = "source")
    val source: String? = null,
    @Json(name = "images")
    val images: List<HeadlineImage>? = null,
    @Json(name = "video")
    val video: List<HeadlineVideo>? = null,
    @Json(name = "categories")
    val categories: List<HeadlineCategory>? = null,
    @Json(name = "ad")
    val ad: AdInfo? = null,
    @Json(name = "tracking")
    val tracking: TrackingInfo? = null,
    @Json(name = "keywords")
    val keywords: List<String>? = null,
    @Json(name = "story")
    val story: String? = null,
    @Json(name = "premium")
    val premium: Boolean? = null,
    @Json(name = "isLiveBlog")
    val isLiveBlog: Boolean? = null,
    @Json(name = "links")
    val links: HeadlineLinks? = null,
    @Json(name = "allowAMP")
    val allowAMP: Boolean? = null,
    @Json(name = "allowAds")
    val allowAds: Boolean? = null,
    @Json(name = "allowCommerce")
    val allowCommerce: Boolean? = null,
    @Json(name = "allowComments")
    val allowComments: Boolean? = null,
    @Json(name = "allowSearch")
    val allowSearch: Boolean? = null,
    @Json(name = "allowContentReactions")
    val allowContentReactions: Boolean? = null
)

@JsonClass(generateAdapter = true)
data class HeadlineImage(
    @Json(name = "dataSourceIdentifier")
    val dataSourceIdentifier: String? = null,
    @Json(name = "id")
    val id: Long? = null,
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
data class HeadlineVideo(
    @Json(name = "id")
    val id: Long? = null,
    @Json(name = "dataSourceIdentifier")
    val dataSourceIdentifier: String? = null,
    @Json(name = "cerebroId")
    val cerebroId: String? = null,
    @Json(name = "pccId")
    val pccId: String? = null,
    @Json(name = "source")
    val source: String? = null,
    @Json(name = "headline")
    val headline: String? = null,
    @Json(name = "shortHeadline")
    val shortHeadline: String? = null,
    @Json(name = "caption")
    val caption: String? = null,
    @Json(name = "title")
    val title: String? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "shortDescription")
    val shortDescription: String? = null,
    @Json(name = "lastModified")
    val lastModified: String? = null,
    @Json(name = "originalPublishDate")
    val originalPublishDate: String? = null,
    @Json(name = "premium")
    val premium: Boolean? = null,
    @Json(name = "syndicatable")
    val syndicatable: Boolean? = null,
    @Json(name = "duration")
    val duration: Int? = null,
    @Json(name = "videoRatio")
    val videoRatio: String? = null,
    @Json(name = "timeRestrictions")
    val timeRestrictions: TimeRestrictions? = null,
    @Json(name = "deviceRestrictions")
    val deviceRestrictions: DeviceRestrictions? = null,
    @Json(name = "thumbnail")
    val thumbnail: String? = null,
    @Json(name = "images")
    val images: List<HeadlineImage>? = null,
    @Json(name = "posterImages")
    val posterImages: PosterImages? = null,
    @Json(name = "links")
    val links: VideoLinks? = null,
    @Json(name = "categories")
    val categories: List<HeadlineCategory>? = null
)

@JsonClass(generateAdapter = true)
data class TimeRestrictions(
    @Json(name = "embargoDate")
    val embargoDate: String? = null,
    @Json(name = "expirationDate")
    val expirationDate: String? = null
)

@JsonClass(generateAdapter = true)
data class DeviceRestrictions(
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "devices")
    val devices: List<String>? = null
)

@JsonClass(generateAdapter = true)
data class PosterImages(
    @Json(name = "default")
    val default: ImageVariant? = null,
    @Json(name = "full")
    val full: ImageVariant? = null,
    @Json(name = "wide")
    val wide: ImageVariant? = null,
    @Json(name = "square")
    val square: ImageVariant? = null
)

@JsonClass(generateAdapter = true)
data class ImageVariant(
    @Json(name = "href")
    val href: String? = null,
    @Json(name = "width")
    val width: Int? = null,
    @Json(name = "height")
    val height: Int? = null
)

@JsonClass(generateAdapter = true)
data class VideoLinks(
    @Json(name = "web")
    val web: WebVideoLink? = null,
    @Json(name = "mobile")
    val mobile: MobileVideoLink? = null,
    @Json(name = "api")
    val api: ApiVideoLink? = null,
    @Json(name = "source")
    val source: VideoSourceLink? = null,
    @Json(name = "sportscenter")
    val sportscenter: LinkHref? = null
)

@JsonClass(generateAdapter = true)
data class WebVideoLink(
    @Json(name = "href")
    val href: String? = null,
    @Json(name = "self")
    val self: SelfLink? = null
)

@JsonClass(generateAdapter = true)
data class MobileVideoLink(
    @Json(name = "source")
    val source: LinkHref? = null,
    @Json(name = "alert")
    val alert: LinkHref? = null,
    @Json(name = "streaming")
    val streaming: LinkHref? = null,
    @Json(name = "progressiveDownload")
    val progressiveDownload: LinkHref? = null
)

@JsonClass(generateAdapter = true)
data class ApiVideoLink(
    @Json(name = "self")
    val self: LinkHref? = null,
    @Json(name = "artwork")
    val artwork: LinkHref? = null
)

@JsonClass(generateAdapter = true)
data class VideoSourceLink(
    @Json(name = "href")
    val href: String? = null,
    @Json(name = "mezzanine")
    val mezzanine: LinkHref? = null,
    @Json(name = "flash")
    val flash: LinkHref? = null,
    @Json(name = "hds")
    val hds: LinkHref? = null,
    @Json(name = "HLS")
    val hls: HLSLinks? = null,
    @Json(name = "HD")
    val hd: LinkHref? = null,
    @Json(name = "full")
    val full: LinkHref? = null
)

@JsonClass(generateAdapter = true)
data class HLSLinks(
    @Json(name = "href")
    val href: String? = null,
    @Json(name = "HD")
    val hd: LinkHref? = null,
    @Json(name = "cmaf")
    val cmaf: LinkHref? = null,
    @Json(name = "shield")
    val shield: LinkHref? = null
)

@JsonClass(generateAdapter = true)
data class HeadlineCategory(
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
    @Json(name = "topicId")
    val topicId: Int? = null
)

@JsonClass(generateAdapter = true)
data class AdInfo(
    @Json(name = "sport")
    val sport: String? = null,
    @Json(name = "bundle")
    val bundle: String? = null
)

@JsonClass(generateAdapter = true)
data class TrackingInfo(
    @Json(name = "sportName")
    val sportName: String? = null,
    @Json(name = "leagueName")
    val leagueName: String? = null,
    @Json(name = "coverageType")
    val coverageType: String? = null,
    @Json(name = "trackingName")
    val trackingName: String? = null,
    @Json(name = "trackingId")
    val trackingId: String? = null,
    @Json(name = "program")
    val program: String? = null
)

@JsonClass(generateAdapter = true)
data class HeadlineLinks(
    @Json(name = "web")
    val web: WebHeadlineLink? = null,
    @Json(name = "mobile")
    val mobile: MobileHeadlineLink? = null,
    @Json(name = "api")
    val api: ApiHeadlineLink? = null,
    @Json(name = "app")
    val app: AppLink? = null
)

@JsonClass(generateAdapter = true)
data class WebHeadlineLink(
    @Json(name = "href")
    val href: String? = null
)

@JsonClass(generateAdapter = true)
data class MobileHeadlineLink(
    @Json(name = "href")
    val href: String? = null
)

@JsonClass(generateAdapter = true)
data class ApiHeadlineLink(
    @Json(name = "self")
    val self: LinkHref? = null
)

@JsonClass(generateAdapter = true)
data class AppLink(
    @Json(name = "sportscenter")
    val sportscenter: LinkHref? = null
)

@JsonClass(generateAdapter = true)
data class BreakingNews(
    @Json(name = "id")
    val id: Long? = null,
    @Json(name = "headline")
    val headline: String? = null,
    @Json(name = "description")
    val description: String? = null
)

