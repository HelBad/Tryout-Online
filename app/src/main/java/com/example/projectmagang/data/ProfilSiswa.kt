package com.example.projectmagang.data

import com.google.gson.annotations.SerializedName

data class ProfilSiswa(
    @SerializedName("nama") val nama:String,
    @SerializedName("jenkel") val jenkel:String,
    @SerializedName("tempatLahir") val tempatLahir:String,
    @SerializedName("tanggalLahir") val tanggalLahir:String,
    @SerializedName("telp") val telp:Int,
    @SerializedName("alamat") val alamat:String,
    @SerializedName("email") val email:String,
    @SerializedName("username") val username:String,
    @SerializedName("nisn") val nisn:String
)