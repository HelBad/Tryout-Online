package com.example.projectmagang.guru.nilai

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.adapter.NilaiGuruAdapter
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.modul.ResponseDataNilaiGuru
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityNilai : AppCompatActivity() {
    lateinit var nilaiGuruAdapter: NilaiGuruAdapter
    lateinit var SP : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nilai_guru)

        SP = getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        getNilai(SP.getString("id_user","").toString(),intent.getStringExtra("nama_guru"))
        nilaiGuruAdapter = NilaiGuruAdapter(applicationContext, arrayListOf())

        val rvNilai = findViewById<RecyclerView>(R.id.rvNilai)
        rvNilai.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = nilaiGuruAdapter
        }
    }

    fun getNilai(id : String, nama_guru : String) {
        UtilsAPI().apiService.guruNilai(id, nama_guru).enqueue(object : Callback<ResponseDataNilaiGuru> {
            override fun onFailure(call: Call<ResponseDataNilaiGuru>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<ResponseDataNilaiGuru>, response: Response<ResponseDataNilaiGuru>) {
                if(response.isSuccessful){
                    val data = response.body()
                    nilaiGuruAdapter.setData(data!!.data)
                }
            }
        })
    }
}