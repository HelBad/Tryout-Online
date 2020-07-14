package com.example.projectmagang.modul

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DataSiswaResponse(recordsiswa:List<Siswa>) {
    @SerializedName("siswa")
    @Expose
    var recordsiswa: List<Siswa>? = null
    init{
        this.recordsiswa = recordsiswa
    }
}