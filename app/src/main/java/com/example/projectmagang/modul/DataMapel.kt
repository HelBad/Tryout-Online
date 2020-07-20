package com.example.projectmagang.modul

import com.google.gson.annotations.SerializedName

data class DataMapel(
    @SerializedName("id") val id: String?,
    @SerializedName("nama_mapel") val nama_mapel : String?,
    @SerializedName("deskripsi") val deskripsi : String?,
    @SerializedName("kkm") val kkm : Int?,
    @SerializedName("durasi") val durasi : Int?,
    @SerializedName("nama_guru") val nama_guru : String?,
    @SerializedName("nama_kelas") val nama_kelas : String?
)

data class ResponseListDataMapel (
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("data") val data : List<DataMapel>
)