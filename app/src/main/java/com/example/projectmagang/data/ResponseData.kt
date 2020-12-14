package com.example.projectmagang.data

import com.google.gson.annotations.SerializedName

data class ResponseListDataJadwal(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("jadwal") val jadwal : List<DataJadwal>
)

data class ResponseListDataJadwalSiswa (
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("jadwal") val jadwal : List<DataJadwalSiswa>
)

data class ResponseListDataMapel (
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("mapel") val mapel : List<DataMapel>
)

data class ResponseListDataMapelDiajukan(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("mapel") val mapel : List<DataMapelDiajukan>
)

data class ResponseListDataNilai(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("nilai") val nilai : List<DataNilai>
)

data class ResponseListDataSiswa(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("siswa") val siswa : List<DataSiswa>
)

data class ResponseListDataSoal(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("soal") val soal : List<DataSoal>
)

data class ResponseListDataSoalSiswa(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("soal") val soal : List<DataSoalSiswa>
)

data class ResponseLogin(
    @SerializedName("response") val response:Boolean,
    @SerializedName("level") val level: String,
    @SerializedName("message") val message: String,
    @SerializedName("id") val id: String
)

data class ResponseMessage(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message:String
)

data class CekLevel(
    @SerializedName("response") val response:Boolean,
    @SerializedName("level") val level:String,
    @SerializedName("message") val message:String
)