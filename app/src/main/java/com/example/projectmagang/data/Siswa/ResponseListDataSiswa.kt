package com.example.projectmagang.data.Siswa

import com.google.gson.annotations.SerializedName

data class ResponseListDataSiswa(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("siswa") val siswa : List<DataSiswa>
)