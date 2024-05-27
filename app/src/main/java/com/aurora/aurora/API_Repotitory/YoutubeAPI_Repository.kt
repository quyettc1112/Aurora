package com.aurora.aurora.API_Repotitory

import com.aurora.aurora.API_Services.YoutubeAPI_Service
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class YoutubeAPI_Repository @Inject constructor(
    private val youtubeapiService: YoutubeAPI_Service
) {

    fun getVideoList(
         id: String,
         apiKey: String,
         part: String
    ) = youtubeapiService.getVideoList(id, apiKey,part)
}