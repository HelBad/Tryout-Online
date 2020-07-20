package com.example.projectmagang.api

import com.example.projectmagang.modul.*
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.*
import java.math.BigInteger

interface BaseApiService {
    @FormUrlEncoded
    @POST("profil_siswa")
    fun readData(@Field("nama") nama:String,
                 @Field("nisn") nisn:String,
                 @Field("alamat") alamat:String): Call<DataSiswaResponse>

    @FormUrlEncoded
    @POST("login_user")
    fun loginUser(@Field("username") username : String,
                  @Field("password") password : String): Call<LoginResponse>

    @FormUrlEncoded
    @POST("profil_guru")
    fun profilGuru(@Field("id") id : String) : Call<ProfilGuru>

    @FormUrlEncoded
    @POST("profil_siswa")
    fun profilSiswa(@Field("id") id : String) : Call<SiswaResponse>

    @FormUrlEncoded
    @POST("cek_level")
    fun cekLevel(@Field("id") id : String) : Call<LoginResponse>

    @FormUrlEncoded
    @POST("edit_profil")
    fun updateGuru(@Field("id") id : String,
                   @Field("nama") nama : String,
                   @Field("alamat") jenis_kelamin : String,
                   @Field("alamat") tempat_lahir : String,
                   @Field("alamat") tanggal_lahir : String,
                   @Field("alamat") telp : String,
                   @Field("alamat") alamat : String,
                   @Field("alamat") email : String,
                   @Field("alamat") username : String,
                   @Field("alamat") nip : String,
                   @Field("alamat") foto : String) : Call<CekMessage>

}