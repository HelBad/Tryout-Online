package com.example.projectmagang.api

import com.example.projectmagang.model.ResponseDataMapel
import com.example.projectmagang.model.ResponseDataNilaiGuru
import com.example.projectmagang.model.*
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
    @POST("nilai_guru")
    fun nilaiGuru(@Field("id") id: String,
                  @Field("id_mapel") id_mapel: Int) :Call<ResponseListDataNilaiGuru>

    @FormUrlEncoded
    @POST("nilai_siswa")
    fun siswaNilai(@Field("id") id: String) :Call<ResponseDataNilaiSiswa>

    @FormUrlEncoded
    @POST("load_jadwal")
    fun listJadwal(@Field("id") id: String) :Call<ResponseDataJadwalSiswa>

    @FormUrlEncoded
    @POST("load_soal")
    fun soalSiswa(@Field("id") id: String,
                  @Field("id_mapel") id_mapel: String) :Call<ResponseDataSoalSiswa>

    @FormUrlEncoded
    @POST("tambah_mapel_diajukan")
    fun tambahMapelDiajukan(@Field("id_mapel") id_mapel: Int,
                            @Field("id") id : String) : Call<ResponseMessage>

    @GET("mapel_tersedia")
    fun mapelTersedia(): Call<ResponseListDataMapelDiajukan>

    @FormUrlEncoded
    @POST("tambah_soal")
    fun tambahSoal(@Field("id_mapel") id_mapel : Int,
                   @Field("soal") soal : String,
                   @Field("kunci") kunci : String,
                   @Field("pila") pila : String,
                   @Field("pilb") pilb : String,
                   @Field("pilc") pilc : String,
                   @Field("pild") pild : String,
                   @Field("pile") pile : String) : Call<ResponseMessage>

    @FormUrlEncoded
    @POST("mapel_guru")
    fun guruMapel(@Field("id") id: String) :Call<ResponseListDataMapel>

    @GET("dashboard")
    fun dashboard(): Call<KetDashboard>

    @FormUrlEncoded
    @POST("load_jadwal_guru")
    fun loadJadwalGuru(@Field("id") id : String) : Call<ResponseListDataJadwal>

    @FormUrlEncoded
    @POST("mapel_soal")
    fun mapelSoal(@Field("id_mapel") id_mapel : Int) : Call<ResponseListDataSoal>

    @FormUrlEncoded
    @POST("delete_soal")
    fun deleteSoal(@Field("id_soal") id_soal : Int) : Call<ResponseMessage>

    @FormUrlEncoded
    @POST("edit_soal")
    fun editSoal(@Field("id_soal") id_soal : Int,
                 @Field("soal") soal : String,
                 @Field("kunci") kunci : String,
                 @Field("pila") pila : String,
                 @Field("pilb") pilb : String,
                 @Field("pilc") pilc : String,
                 @Field("pild") pild : String,
                 @Field("pile") pile : String) : Call<ResponseMessage>

    @FormUrlEncoded
    @POST("profil_guru")
    fun getNilai(@Field("id") id : String) : Call<DataSoalSiswa>

    @FormUrlEncoded
    @POST("siswa_jawab")
    fun siswaJawab(@Field("id") id: String,
                   @Field("id_mapel") id_mapel: Int,
                   @Field("jawab[]") jawab: ArrayList<String>) :Call<ResponseMessage>

}