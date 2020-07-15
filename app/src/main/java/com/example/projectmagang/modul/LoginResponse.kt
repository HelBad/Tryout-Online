package com.example.projectmagang.modul

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("response") val response:Boolean,
    @SerializedName("level") val level: String,
    @SerializedName("message") val message: String
)