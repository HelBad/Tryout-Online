package com.example.projectmagang.data.Nilai

import com.google.gson.annotations.SerializedName

data class ResponseListDataNilai(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("nilai") val nilai : List<DataNilai>
)