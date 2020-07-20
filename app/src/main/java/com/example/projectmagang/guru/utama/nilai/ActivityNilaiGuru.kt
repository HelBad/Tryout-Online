package com.example.projectmagang.guru.utama.nilai

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.modul.ResponseListDataNilaiGuru
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityNilaiGuru : AppCompatActivity() {
    lateinit var nilaiGuruAdapter: NilaiGuruAdapter
    lateinit var SP : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nilai_guru)

        SP = getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        getNilai(SP.getString("id_user","").toString(),intent.getStringExtra("nama_guru"))
        nilaiGuruAdapter = NilaiGuruAdapter (applicationContext, arrayListOf())
        val rvNilai = findViewById<RecyclerView>(R.id.rvNilai)
        rvNilai.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = nilaiGuruAdapter
        }
    }

    fun getNilai(id : String, nama_guru : String) {
        UtilsAPI().apiService.guruNilai(id, nama_guru).enqueue(object : Callback<ResponseListDataNilaiGuru> {
            override fun onFailure(call: Call<ResponseListDataNilaiGuru>, t: Throwable) {
                t.printStackTrace()
                showMessage("gagal load data")
            }
            override fun onResponse(call: Call<ResponseListDataNilaiGuru>, response: Response<ResponseListDataNilaiGuru>) {
                if(response.isSuccessful){
                    val data = response.body()
                    nilaiGuruAdapter.setData(data!!.data)
                }
            }
        })
    }

    fun showMessage(msg : String){
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}