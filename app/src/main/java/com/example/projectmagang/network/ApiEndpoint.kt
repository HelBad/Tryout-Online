package com.example.projectmagang.network

import com.example.projectmagang.data.ProfilGuru
import com.example.projectmagang.data.ProfilSiswa
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

    @FormUrlEncoded
    @POST("profil_guru")
    fun profilGuru(
        @Field("id") id : String
    ) : Call<ProfilGuru>

    @FormUrlEncoded
    @POST("profil_siswa")
    fun profilSiswa(
        @Field("id") id : String
    ) : Call<ProfilSiswa>
}