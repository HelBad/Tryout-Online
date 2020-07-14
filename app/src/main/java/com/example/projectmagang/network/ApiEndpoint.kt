package com.example.projectmagang.network

import com.example.projectmagang.data.ResponseLogin
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiEndpoint{

    @FormUrlEncoded
    @POST("login_user")
    fun loginUser(
        @Field("username") username : String,
        @Field("password") password : String
    ): Call<ResponseLogin>
}