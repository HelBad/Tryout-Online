package com.example.projectmagang.model

import com.google.gson.annotations.SerializedName

data class ResponseListDataMapelDiajukan(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("mapel") val mapel : List<DataMapelDiajukan>
)