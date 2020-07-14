package com.example.projectmagang.modul

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Siswa(id_user:String, nisn:String, nama:String, email:String, jenis_kelamin:String, telp:String, alamat:String,
             tanggal_lahir:String, tempat_lahir:String, username:String, nama_kelas:String, nama_jurusan:String) {
    @SerializedName("id_user")
    @Expose
    var id_user:String
    @SerializedName("nisn")
    @Expose
    var nisn:String
    @SerializedName("nama")
    @Expose
    var nama:String
    @SerializedName("email")
    @Expose
    var email:String
    @SerializedName("jenis_kelamin")
    @Expose
    var jenis_kelamin:String
    @SerializedName("telp")
    @Expose
    var telp:String
    @SerializedName("alamat")
    @Expose
    var alamat:String
    @SerializedName("tanggal_lahir")
    @Expose
    var tanggal_lahir:String
    @SerializedName("tempat_lahir")
    @Expose
    var tempat_lahir:String
    @SerializedName("username")
    @Expose
    var username:String
    @SerializedName("nama_kelas")
    @Expose
    var nama_kelas:String
    @SerializedName("nama_jurusan")
    @Expose
    var nama_jurusan:String

    init{
        this.id_user = id_user
        this.nisn = nisn
        this.nama = nama
        this.email = email
        this.jenis_kelamin = jenis_kelamin
        this.telp = telp
        this.alamat = alamat
        this.tanggal_lahir = tanggal_lahir
        this.tempat_lahir = tempat_lahir
        this.username = username
        this.nama_kelas = nama_kelas
        this.nama_jurusan = nama_jurusan
    }
}