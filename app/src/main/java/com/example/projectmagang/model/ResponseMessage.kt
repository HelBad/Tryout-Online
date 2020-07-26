package com.example.projectmagang.model

import com.google.gson.annotations.SerializedName

data class ResponseMessage(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message:String
)