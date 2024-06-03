package com.aurora.aurora.API_Repotitory

import com.aurora.aurora.API_Services.UserAPI_Service
import com.aurora.aurora.API_Services.YoutubeAPI_Service
import com.aurora.aurora.Model.RequestDTO.UserCretidentialDTO
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class UserAPI_Repository @Inject constructor(
    private val userapiService: UserAPI_Service
) {
    fun getUserCretidential(
        userCretidentialDTO: UserCretidentialDTO
    ) = userapiService.getUserCretidential(userCretidentialDTO)

}