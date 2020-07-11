package com.example.projectmagang.modul

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReadDataResponse(records:List<Record>) {
    @SerializedName("guru")
    @Expose
    var records: List<Record>? = null
    init{
        this.records = records
    }
}