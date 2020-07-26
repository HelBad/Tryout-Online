package com.example.projectmagang.model

import com.google.gson.annotations.SerializedName

data class DataMapelDiajukan(
    @SerializedName("id") val id: Int,
    @SerializedName("nama_mapel") val nama_mapel : String,
    @SerializedName("nama_kelas") val nama_kelas : String
)