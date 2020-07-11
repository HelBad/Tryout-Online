package com.example.projectmagang.api

import com.example.projectmagang.modul.ReadDataResponse
import com.example.projectmagang.modul.Record
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BaseApiService {
    @FormUrlEncoded
    @POST("load_guru")
    fun readData(@Field("nama") nama:String,
                 @Field("nip") nip:String,
                 @Field("id_mapel") id_mapel:String): Call<ReadDataResponse>

    @FormUrlEncoded
    @POST("load_guru")
    fun getReponse(@Field("nama") nama:String): Call<Record>
}