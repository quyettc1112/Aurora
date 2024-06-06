package com.aurora.aurora.API_Services

import com.aurora.aurora.Model.RequestDTO.UserCretidentialDTO
import com.aurora.aurora.Model.Respone.JWTObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface UserAPI_Service {

    @POST("/auth/sign-in")
    fun getUserCretidential(
        @Body userCretidentialDTO: UserCretidentialDTO
    ): Call<JWTObject>

}