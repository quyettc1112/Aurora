package com.aurora.aurora.Model.Respone

import com.google.gson.annotations.SerializedName

data class YouTubeVideoListResponse(
    @SerializedName("kind") val kind: String,
    @SerializedName("etag") val etag: String,
    @SerializedName("items") val items: List<VideoItem>,
    @SerializedName("pageInfo") val pageInfo: PageInfo
) {
    data class VideoItem(
        @SerializedName("kind") val kind: String,
        @SerializedName("etag") val etag: String,
        @SerializedName("id") val id: String,
        @SerializedName("snippet") val snippet: Snippet,
        @SerializedName("contentDetails") val contentDetails: ContentDetails,
        @SerializedName("status") val status: Status,
        @SerializedName("statistics") val statistics: Statistics
    )

    data class Snippet(
        @SerializedName("publishedAt") val publishedAt: String,
        @SerializedName("channelId") val channelId: String,
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String,
        @SerializedName("thumbnails") val thumbnails: Thumbnails,
        @SerializedName("channelTitle") val channelTitle: String,
        @SerializedName("tags") val tags: List<String>,
        @SerializedName("categoryId") val categoryId: String,
        @SerializedName("liveBroadcastContent") val liveBroadcastContent: String,
        @SerializedName("localized") val localized: Localized
    )

    data class Thumbnails(
        @SerializedName("default") val default: Thumbnail,
        @SerializedName("medium") val medium: Thumbnail,
        @SerializedName("high") val high: Thumbnail
    )

    data class Thumbnail(
        @SerializedName("url") val url: String,
        @SerializedName("width") val width: Int,
        @SerializedName("height") val height: Int
    )

    data class Localized(
        @SerializedName("title") val title: String,
        @SerializedName("description") val description: String
    )

    data class ContentDetails(
        @SerializedName("duration") val duration: String,
        @SerializedName("dimension") val dimension: String,
        @SerializedName("definition") val definition: String,
        @SerializedName("caption") val caption: String,
        @SerializedName("licensedContent") val licensedContent: Boolean,
        @SerializedName("projection") val projection: String
    )


    data class Status(
        @SerializedName("uploadStatus") val uploadStatus: String,
        @SerializedName("privacyStatus") val privacyStatus: String,
        @SerializedName("license") val license: String,
        @SerializedName("embeddable") val embeddable: Boolean,
        @SerializedName("publicStatsViewable") val publicStatsViewable: Boolean,
        @SerializedName("madeForKids") val madeForKids: Boolean
    )

    data class Statistics(
        @SerializedName("viewCount") val viewCount: String,
        @SerializedName("likeCount") val likeCount: String,
        @SerializedName("favoriteCount") val favoriteCount: String,
        @SerializedName("commentCount") val commentCount: String
    )

    data class PageInfo(
        @SerializedName("totalResults") val totalResults: Int,
        @SerializedName("resultsPerPage") val resultsPerPage: Int
    )



}

