package com.example.projectmagang.modul

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class Siswa(
    @SerializedName("id") val id:String?,
    @SerializedName("nisn") val nisn:String?,
    @SerializedName("nama") val nama:String?,
    @SerializedName("email") val email:String?,
    @SerializedName("jenis_kelamin") val jenis_kelamin:String?,
    @SerializedName("telp") val telp: BigInteger?,
    @SerializedName("alamat") val alamat:String?,
    @SerializedName("tanggal_lahir") val tanggal_lahir:String?,
    @SerializedName("tempat_lahir") val tempat_lahir:String?,
    @SerializedName("username") val username:String?,
//    @SerializedName("nama_kelas") val nama_kelas:String?,
//    @SerializedName("nama_jurusan") val nama_jurusan:String?,
    @SerializedName("foto") val foto:String?
)