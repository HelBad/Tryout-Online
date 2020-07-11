package com.example.projectmagang.modul

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Record(id_user:String, nip:String, nama:String, email:String, jenis_kelamin:String, telp:String,
             alamat:String, tanggal_lahir:String, tempat_lahir:String, username:String, id_mapel:String) {
    @SerializedName("id_user")
    @Expose
    var id_user:String
    @SerializedName("nip")
    @Expose
    var nip:String
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
    @SerializedName("id_mapel")
    @Expose
    var id_mapel:String

    init{
        this.id_user = id_user
        this.nip = nip
        this.nama = nama
        this.email = email
        this.jenis_kelamin = jenis_kelamin
        this.telp = telp
        this.alamat = alamat
        this.tanggal_lahir = tanggal_lahir
        this.tempat_lahir = tempat_lahir
        this.username = username
        this.id_mapel = id_mapel
    }
}