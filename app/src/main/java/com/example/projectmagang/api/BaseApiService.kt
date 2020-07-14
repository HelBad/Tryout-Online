package com.example.projectmagang.api

import com.example.projectmagang.modul.DataSiswaResponse
import retrofit2.Call
import retrofit2.http.*

interface BaseApiService {
    @FormUrlEncoded
    @POST("load_siswa")
    fun readData(@Field("nama") nama:String,
                 @Field("nisn") nisn:String,
                 @Field("nama_kelas") nama_kelas:String): Call<DataSiswaResponse>

}