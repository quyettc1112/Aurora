package com.aurora.aurora.API_Services

import com.aurora.aurora.Model.Respone.YouTubeVideoListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeAPI_Service {

    @GET("videos")
    fun getVideoList(
        @Query("id") id: String,
        @Query("key") apiKey: String,
        @Query("part") part: String = "snippet,contentDetails,statistics,status"
    ): Call<YouTubeVideoListResponse>
}