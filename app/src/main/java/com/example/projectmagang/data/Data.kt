package com.example.projectmagang.data

import com.google.gson.annotations.SerializedName

data class DataJadwal(
    @SerializedName("nama_mapel") val nama_mapel : String,
    @SerializedName("nama_kelas") val nama_kelas : String,
    @SerializedName("waktu") val waktu : String,
    @SerializedName("tanggal") val tanggal : String
)

data class DataJadwalSiswa(
    @SerializedName("id") val id: String?,
    @SerializedName("id_mapel") val id_mapel : Int?,
    @SerializedName("mapel") val mapel : String?,
    @SerializedName("kelas") val kelas : String?,
    @SerializedName("tanggal") val tanggal : String?,
    @SerializedName("waktu") val waktu : String?,
    @SerializedName("durasi") val durasi : Int?,
    @SerializedName("nilai") val nilai : Float?
)

data class DataMapel(
    @SerializedName("id") val id: Int,
    @SerializedName("nama_mapel") val nama_mapel : String,
    @SerializedName("deskripsi") val deskripsi : String,
    @SerializedName("kkm") val kkm : Int,
    @SerializedName("durasi") val durasi : Int,
    @SerializedName("nama_guru") val nama_guru : String,
    @SerializedName("nama_kelas") val nama_kelas : String
)

data class DataMapelDiajukan(
    @SerializedName("id") val id: Int,
    @SerializedName("nama_mapel") val nama_mapel : String,
    @SerializedName("nama_kelas") val nama_kelas : String
)

data class DataNilai (
    @SerializedName("nisn") val nisn : Long,
    @SerializedName("nama_siswa") val nama_siswa : String,
    @SerializedName("nama_jurusan") val nama_jurusan : String,
    @SerializedName("nama_kelas") val nama_kelas : String,
    @SerializedName("nama_mapel") val nama_mapel : String,
    @SerializedName("nama_guru") val nama_guru : String,
    @SerializedName("nilai") val nilai : Double
)

data class DataSiswa(
    @SerializedName("nisn") val nisn : String,
    @SerializedName("nama") val nama : String,
    @SerializedName("foto") val foto : String,
    @SerializedName("jenis_kelamin") val jenis_kelamin : String,
    @SerializedName("telp") val telp : String,
    @SerializedName("alamat") val alamat : String,
    @SerializedName("tempat_lahir") val tempat_lahir : String,
    @SerializedName("tanggal_lahir") val tanggal_lahir : String,
    @SerializedName("email") val email : String,
    @SerializedName("nama_kelas") val nama_kelas : String
)

data class DataSoal(
    @SerializedName("id") val id : Int,
    @SerializedName("soal") val soal : String,
    @SerializedName("jawab_a") val jawab_a : String,
    @SerializedName("jawab_b") val jawab_b : String,
    @SerializedName("jawab_c") val jawab_c : String,
    @SerializedName("jawab_d") val jawab_d : String,
    @SerializedName("jawab_e") val jawab_e : String,
    @SerializedName("kunci") val kunci : String
)

data class DataSoalSiswa (
    @SerializedName("id") val id : String,
    @SerializedName("id_mapel") val id_mapel : String,
    @SerializedName("soal") val soal : String,
    @SerializedName("jawab_a") val jawab_a : String,
    @SerializedName("jawab_b") val jawab_b : String,
    @SerializedName("jawab_c") val jawab_c : String,
    @SerializedName("jawab_d") val jawab_d : String,
    @SerializedName("jawab_e") val jawab_e : String,
    @SerializedName("kunci") val kunci : String
)

data class ProfilGuru(
    @SerializedName("id") val id:String,
    @SerializedName("nama") val nama:String,
    @SerializedName("jenkel") val jenkel:String,
    @SerializedName("tempat_lahir") val tempat_lahir:String,
    @SerializedName("tanggal_lahir") val tanggal_lahir:String,
    @SerializedName("telp") val telp:String,
    @SerializedName("alamat") val alamat:String,
    @SerializedName("email") val email:String,
    @SerializedName("username") val username:String,
    @SerializedName("nip") val nip:String,
    @SerializedName("foto") val foto:String
)

data class ProfilSiswa(
    @SerializedName("id") val id:String,
    @SerializedName("nama") val nama:String,
    @SerializedName("jenkel") val jenkel:String,
    @SerializedName("tempat_lahir") val tempat_lahir:String,
    @SerializedName("tanggal_lahir") val tanggal_lahir:String,
    @SerializedName("telp") val telp:String,
    @SerializedName("alamat") val alamat:String,
    @SerializedName("email") val email:String,
    @SerializedName("username") val username:String,
    @SerializedName("nisn") val nisn:String,
    @SerializedName("foto") val foto:String,
    @SerializedName("kelas") val kelas:String,
    @SerializedName("jurusan") val jurusan:String
)

data class KetDashboard(
    @SerializedName("response") val response : Boolean,
    @SerializedName("message") val message : String,
    @SerializedName("jumlah_kelas") val jumlah_kelas : Int,
    @SerializedName("jumlah_siswa") val jumlah_siswa : Int,
    @SerializedName("jumlah_guru") val jumlah_guru : Int,
    @SerializedName("jumlah_mapel") val jumlah_mapel : Int
)