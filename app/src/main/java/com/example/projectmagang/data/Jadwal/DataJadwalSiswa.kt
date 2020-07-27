package com.example.projectmagang.data.Jadwal

import com.google.gson.annotations.SerializedName

data class DataJadwalSiswa(
    @SerializedName("id") val id: String?,
    @SerializedName("id_mapel") val id_mapel : Int?,
    @SerializedName("mapel") val mapel : String?,
    @SerializedName("kelas") val kelas : String?,
    @SerializedName("tanggal") val tanggal : String?,
    @SerializedName("waktu") val waktu : String?
)
