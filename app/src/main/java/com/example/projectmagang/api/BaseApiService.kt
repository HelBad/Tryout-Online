package com.example.projectmagang.api

import com.example.projectmagang.modul.ResponseDataMapel
import com.example.projectmagang.modul.ResponseDataNilaiGuru
import com.example.projectmagang.modul.*
import retrofit2.Call
import retrofit2.http.*

interface BaseApiService {
    @FormUrlEncoded
    @POST("load_siswa_where_guru")
    fun listSiswa(@Field("id") id:String): Call<ResponseDataSiswa>

    @FormUrlEncoded
    @POST("login_user")
    fun loginUser(@Field("username") username : String,
                  @Field("password") password : String): Call<ResponseLogin>

    @FormUrlEncoded
    @POST("profil_guru")
    fun profilGuru(@Field("id") id : String) : Call<ProfilGuru>

    @FormUrlEncoded
    @POST("profil_siswa")
    fun profilSiswa(@Field("id") id : String) : Call<ProfilSiswa>

    @FormUrlEncoded
    @POST("cek_level")
    fun cekLevel(@Field("id") id : String) : Call<ResponseLogin>

    @FormUrlEncoded
    @POST("edit_profil")
    fun updateProfil(@Field("id") id : String,
                     @Field("nama") nama : String,
                     @Field("jenis_kelamin") jenis_kelamin : String,
                     @Field("tempat_lahir") tempat_lahir : String,
                     @Field("tanggal_lahir") tanggal_lahir : String,
                     @Field("telp") telp : String,
                     @Field("alamat") alamat : String,
                     @Field("email") email : String,
                     @Field("username") username : String,
                     @Field("foto") foto : String) : Call<CekMessage>

    @FormUrlEncoded
    @POST("mapel_guru")
    fun mapelGuru(@Field("id") id: String) :Call<ResponseDataMapel>

    @FormUrlEncoded
    @POST("nilai_guru")
    fun guruNilai(@Field("id") id: String,
                  @Field("nama_guru") nama_guru: String) :Call<ResponseDataNilaiGuru>

    @FormUrlEncoded
    @POST("nilai_siswa")
    fun siswaNilai(@Field("id") id: String) :Call<ResponseDataNilaiSiswa>

    @FormUrlEncoded
    @POST("load_jadwal")
    fun listJadwal(@Field("id") id: String) :Call<ResponseDataJadwal>
}