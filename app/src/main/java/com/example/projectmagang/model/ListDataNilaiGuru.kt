package com.example.projectmagang.model

import com.google.gson.annotations.SerializedName

data class DataNilai (
    @SerializedName("nisn") val nisn : Long,
    @SerializedName("nama_siswa") val nama_siswa : String,
    @SerializedName("nama_jurusan") val nama_jurusan : String,
    @SerializedName("nama_kelas") val nama_kelas : String,
    @SerializedName("nama_mapel") val nama_mapel : String,
    @SerializedName("nama_guru") val nama_guru : String,
    @SerializedName("nilai") val nilai : Double
)

data class ResponseListDataNilaiGuru(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("nilai") val nilai : List<DataNilai>
)