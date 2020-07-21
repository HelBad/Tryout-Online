package com.example.projectmagang.modul

import com.google.gson.annotations.SerializedName

data class DataJadwal(
    @SerializedName("id") val id: String?,
    @SerializedName("mapel") val mapel : String?,
    @SerializedName("kelas") val kelas : String?,
    @SerializedName("tanggal") val tanggal : String?,
    @SerializedName("waktu") val waktu : String?
)

data class ResponseDataJadwal (
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("jadwal") val jadwal : List<DataJadwal>
)