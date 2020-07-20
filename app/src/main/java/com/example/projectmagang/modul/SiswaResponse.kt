package com.example.projectmagang.modul

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SiswaResponse(recordsiswa:Siswa) {
    @SerializedName("siswa")
    @Expose
    var recordsiswa: Siswa? = null
    init{
        this.recordsiswa = recordsiswa
    }
}