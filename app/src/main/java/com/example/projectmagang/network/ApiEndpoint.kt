package com.example.projectmagang.network

import com.example.projectmagang.data.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.math.BigInteger
import java.util.*

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

    @FormUrlEncoded
    @POST("cek_level")
    fun cekLevel(
        @Field("id") id : String
    ) : Call<CekLevel>

    @FormUrlEncoded
    @POST("edit_guru")
    fun editGuru(
        @Field("id") id : String,
        @Field("nama") nama : String,
        @Field("alamat") alamat : String,
        @Field("telp") telp : BigInteger,
        @Field("jenis_kelamin") jenis_kelamin : String,
        @Field("tempat_lahir") tempat_lahir : String,
        @Field("tanggal_lahir") tanggal_lahir : Date
    ) : Call<CekMessage>


}