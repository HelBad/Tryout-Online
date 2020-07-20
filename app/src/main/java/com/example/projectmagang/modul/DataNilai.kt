package com.example.projectmagang.modul

import com.google.gson.annotations.SerializedName

data class DataNilai (
    @SerializedName("nisn") val nisn : Long,
    @SerializedName("nama") val nama : String,
    @SerializedName("nama_jurusan") val nama_jurusan : String,
    @SerializedName("nama_kelas") val nama_kelas : String,
    @SerializedName("nama_mapel") val nama_mapel : String,
    @SerializedName("nama_guru") val nama_guru : String,
    @SerializedName("nilai") val nilai : Double
)

data class ResponseListDataNilaiGuru(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data : List<DataNilai>
)