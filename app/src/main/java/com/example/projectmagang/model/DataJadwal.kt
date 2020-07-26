package com.example.projectmagang.model

import com.google.gson.annotations.SerializedName

data class DataJadwalSiswa(
    @SerializedName("id") val id: String?,
    @SerializedName("id_mapel") val id_mapel : String?,
    @SerializedName("mapel") val mapel : String?,
    @SerializedName("kelas") val kelas : String?,
    @SerializedName("tanggal") val tanggal : String?,
    @SerializedName("waktu") val waktu : String?
)

data class ResponseDataJadwalSiswa (
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("jadwal") val jadwal : List<DataJadwalSiswa>
)

data class DataJadwal(
    @SerializedName("nama_mapel") val nama_mapel : String,
    @SerializedName("nama_kelas") val nama_kelas : String,
    @SerializedName("waktu") val waktu : String,
    @SerializedName("tanggal") val tanggal : String
)