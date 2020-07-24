package com.example.projectmagang.network

import com.example.projectmagang.data.*
import com.example.projectmagang.data.Jadwal.ResponseListDataJadwal
import com.example.projectmagang.data.Mapel.ResponseListDataMapel
import com.example.projectmagang.data.Mapel.ResponseListDataMapelDiajukan
import com.example.projectmagang.data.Nilai.ResponseListDataNilaiGuru
import com.example.projectmagang.data.Siswa.ResponseListDataSiswa
import com.example.projectmagang.data.Soal.ResponseListDataSoal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiEndpoint{

    @FormUrlEncoded
    @POST("login_user")
    fun loginUser(
        @Field("username") username : String,
        @Field("password") password : String
    ): Call<ResponseLogin>

    @FormUrlEncoded
    @POST("profil_guru")
    fun profilGuru(
        @Field("id") id : String
    ) : Call<ProfilGuru>

    @FormUrlEncoded
    @POST("profil_siswa")
    fun profilSiswa(
        @Field("id") id : String
    ) : Call<ProfilSiswa>

    @FormUrlEncoded
    @POST("cek_level")
    fun cekLevel(
        @Field("id") id : String
    ) : Call<CekLevel>

    @FormUrlEncoded
    @POST("edit_profil")
    fun editProfil(
        @Field("id") id : String,
        @Field("nama") nama : String,
        @Field("alamat") alamat : String,
        @Field("telp") telp : Long
//        @Field("jenis_kelamin") jenis_kelamin : String,
//        @Field("tempat_lahir") tempat_lahir : String,
//        @Field("tanggal_lahir") tanggal_lahir : Date
    ) : Call<ResponseMessage>

    @FormUrlEncoded
    @POST("mapel_guru")
    fun mapelGuru(
        @Field("id") id: String
    ) :Call<ResponseListDataMapel>

    @FormUrlEncoded
    @POST("nilai_guru")
    fun guruNilai(
        @Field("id") id: String,
        @Field("id_mapel") id_mapel: Int
    ) :Call<ResponseListDataNilaiGuru>

    @GET("dashboard")
    fun dashboard(): Call<KetDashboard>

//    @FormUrlEncoded
//    @POST("mapel_diajukan")
//    fun mapelDiajukan(
//        @Field("id") id : String
//    ): Call<ResponseListDataMapelDiajukan>

    @GET("mapel_tersedia")
    fun mapelTersedia(): Call<ResponseListDataMapelDiajukan>

    @FormUrlEncoded
    @POST("tambah_mapel_diajukan")
    fun tambahMapelDiajukan(
        @Field("id_mapel") id_mapel: Int,
        @Field("id") id : String
    ) : Call<ResponseMessage>

    @FormUrlEncoded
    @POST("mapel_soal")
    fun mapelSoal(
        @Field("id_mapel") id_mapel : Int
    ) : Call<ResponseListDataSoal>

    @FormUrlEncoded
    @POST("delete_soal")
    fun deleteSoal(
        @Field("id_soal") id_soal : Int
    ) : Call<ResponseMessage>

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
        @Field("pile") pile : String

    ) : Call<ResponseMessage>

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
        @Field("pile") pile : String
    ) : Call<ResponseMessage>


    @FormUrlEncoded
    @POST("siswa_guru")
    fun siswaGuru(
        @Field("id_mapel") id : Int
    ) : Call<ResponseListDataSiswa>


    @FormUrlEncoded
    @POST("load_jadwal_guru")
    fun loadJadwalGuru(
        @Field("id") id : String
    ) : Call<ResponseListDataJadwal>



}