package com.example.projectmagang.model

import com.google.gson.annotations.SerializedName

data class DataJawabanSiswa (
    @SerializedName("id") val id : String,
    @SerializedName("id_siswa") val id_siswa : String,
    @SerializedName("id_mapel") val id_mapel : String,
    @SerializedName("id_soal") val id_soal : String,
    @SerializedName("jawaban") val jawaban : String
)

data class ResponseDataJawabanSiswa(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("jawaban") val jawaban : List<DataJawabanSiswa>
)