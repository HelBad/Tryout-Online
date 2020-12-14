package com.example.projectmagang.network

import com.example.projectmagang.data.*
import com.example.projectmagang.data.ResponseListDataJadwal
import com.example.projectmagang.data.ResponseListDataJadwalSiswa
import com.example.projectmagang.data.ResponseListDataMapel
import com.example.projectmagang.data.ResponseListDataMapelDiajukan
import com.example.projectmagang.data.ResponseListDataNilai
import com.example.projectmagang.data.ResponseListDataSiswa
import com.example.projectmagang.data.ResponseListDataSoal
import com.example.projectmagang.data.ResponseListDataSoalSiswa
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint{
    @FormUrlEncoded
    @POST("login_user")
    fun loginUser(
        @Field("username") username : String,
        @Field("password") password : String): Call<ResponseLogin>

    @FormUrlEncoded
    @POST("profil_guru")
    fun profilGuru(@Field("id") id : String) : Call<ProfilGuru>

    @FormUrlEncoded
    @POST("profil_siswa")
    fun profilSiswa(@Field("id") id : String) : Call<ProfilSiswa>

    @FormUrlEncoded
    @POST("cek_level")
    fun cekLevel(@Field("id") id : String) : Call<CekLevel>

    @Multipart
    @POST("edit_profil_guru")
    fun editProfilGuru(
        @Query("id") id: String,
        @Query("nama") nama: String,
        @Query("alamat") alamat: String,
        @Query("telp") telp: Long,
        @Query("tempat_lahir") tempat_lahir: String,
        @Query("tanggal_lahir") tanggal_lahir: String,
        @Query("jenis_kelamin") jenis_kelamin : String,
        @Part image: MultipartBody.Part?) : Call<ResponseMessage>

    @Multipart
    @POST("edit_profil_siswa")
    fun editProfilSiswa(
        @Query("id") id: String,
        @Query("nama") nama: String,
        @Query("alamat") alamat: String,
        @Query("telp") telp: Long,
        @Query("tempat_lahir") tempat_lahir: String,
        @Query("tanggal_lahir") tanggal_lahir: String,
        @Query("jenis_kelamin") jenis_kelamin : String,
        @Part image: MultipartBody.Part?) : Call<ResponseMessage>

    @FormUrlEncoded
    @POST("mapel_guru")
    fun mapelGuru(@Field("id") id: String) :Call<ResponseListDataMapel>

    @FormUrlEncoded
    @POST("nilai_guru")
    fun guruNilai(
        @Field("id") id: String,
        @Field("id_mapel") id_mapel: Int) :Call<ResponseListDataNilai>

    @FormUrlEncoded
    @POST("nilai_siswa")
    fun siswaNilai(@Field("id") id: String) :Call<ResponseListDataNilai>

    @GET("dashboard")
    fun dashboard(): Call<KetDashboard>

    @GET("mapel_tersedia")
    fun mapelTersedia(): Call<ResponseListDataMapelDiajukan>

    @FormUrlEncoded
    @POST("tambah_mapel_diajukan")
    fun tambahMapelDiajukan(
        @Field("id_mapel") id_mapel: Int,
        @Field("id") id : String) : Call<ResponseMessage>

    @FormUrlEncoded
    @POST("mapel_soal")
    fun mapelSoal(@Field("id_mapel") id_mapel : Int) : Call<ResponseListDataSoal>

    @FormUrlEncoded
    @POST("delete_soal")
    fun deleteSoal(@Field("id_soal") id_soal : Int) : Call<ResponseMessage>

    @FormUrlEncoded
    @POST("tambah_soal")
    fun tambahSoal(
        @Field("id_mapel") id_mapel : Int,
        @Field("soal") soal : String,
        @Field("kunci") kunci : String,
        @Field("pila") pila : String,
        @Field("pilb") pilb : String,
        @Field("pilc") pilc : String,
        @Field("pild") pild : String,
        @Field("pile") pile : String) : Call<ResponseMessage>

    @FormUrlEncoded
    @POST("edit_soal")
    fun editSoal(
        @Field("id_soal") id_soal : Int,
        @Field("soal") soal : String,
        @Field("kunci") kunci : String,
        @Field("pila") pila : String,
        @Field("pilb") pilb : String,
        @Field("pilc") pilc : String,
        @Field("pild") pild : String,
        @Field("pile") pile : String) : Call<ResponseMessage>

    @FormUrlEncoded
    @POST("siswa_guru")
    fun siswaGuru(@Field("id_mapel") id : Int) : Call<ResponseListDataSiswa>

    @FormUrlEncoded
    @POST("load_jadwal_guru")
    fun loadJadwalGuru(@Field("id") id : String) : Call<ResponseListDataJadwal>

    @FormUrlEncoded
    @POST("siswa_jawab")
    fun siswaJawab(
        @Field("id") id: String,
        @Field("id_mapel") id_mapel: Int,
        @Field("jawab[]") jawab: ArrayList<String>) :Call<ResponseMessage>

    @FormUrlEncoded
    @POST("load_jadwal")
    fun listJadwal(@Field("id") id: String) :Call<ResponseListDataJadwalSiswa>

    @FormUrlEncoded
    @POST("load_soal")
    fun soalSiswa(
        @Field("id") id: String,
        @Field("id_mapel") id_mapel: Int) :Call<ResponseListDataSoalSiswa>
}