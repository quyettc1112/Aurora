package com.aurora.aurora.API_Services

import com.aurora.aurora.Model.RequestDTO.RegisterRequestDTO
import com.aurora.aurora.Model.RequestDTO.UserCretidentialDTO
import com.aurora.aurora.Model.Respone.JWTObject
import com.aurora.aurora.Model.Respone.MessageRespone
import com.aurora.aurora.Model.Respone.UserInfoRespone
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface UserAPI_Service {

    @POST("/auth/sign-in")
    fun getUserCretidential(
        @Body userCretidentialDTO: UserCretidentialDTO
    ): Call<JWTObject>

    @POST("/auth/sign-up")
    fun callRegisterUser(
        @Body registerRequestDTO: RegisterRequestDTO
    ): Call<MessageRespone>

    @GET("/auth/me")
    fun getUserInfo(
        @Header("Authorization") authorization: String
    ): Call<UserInfoRespone>

}