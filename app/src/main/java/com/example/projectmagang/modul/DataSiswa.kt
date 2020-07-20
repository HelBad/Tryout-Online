package com.example.projectmagang.modul

import com.google.gson.annotations.SerializedName

data class DataSiswa(
    @SerializedName("id_user") val id_user: String?,
    @SerializedName("nisn") val nisn : String?,
    @SerializedName("nama") val nama : String?,
    @SerializedName("email") val email : String?,
    @SerializedName("jenis_kelamin") val jenis_kelamin : String?,
    @SerializedName("telp") val telp : String?,
    @SerializedName("alamat") val alamat : String?,
    @SerializedName("tanggal_lahir") val tanggal_lahir : String?,
    @SerializedName("tempat_lahir") val tempat_lahir : String?,
    @SerializedName("username") val username : String?,
    @SerializedName("nama_kelas") val nama_kelas : String?
)

data class ResponseDataSiswa (
    @SerializedName("response") val response : Int,
    @SerializedName("message") val message : String,
    @SerializedName("siswa") val siswa : List<DataSiswa>
)