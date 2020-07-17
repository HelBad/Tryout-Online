package com.example.projectmagang.data.Nilai

import com.example.projectmagang.data.CekMessage
import com.google.gson.annotations.SerializedName

data class ResponseListDataNilaiGuru(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("nilai") val nilai : List<DataNilai>
)