package com.example.projectmagang.data

import com.google.gson.annotations.SerializedName

data class DataUser(
    @SerializedName("nama") val nama : String,
    @SerializedName("nip") val nip : Int,
//    @SerializedName("nisn") val nisn : Int,
    @SerializedName("jenkel") val jenkel : String,
    @SerializedName("email") val email : String,
    @SerializedName("username") val username : String,
    @SerializedName("telp") val telp: Int,
    @SerializedName("tempat_lahir") val tempat_lahir : String,
    @SerializedName("tanggal_lahir") val tanggal_lahir : String,
    @SerializedName("alamat") val alamat : String
)