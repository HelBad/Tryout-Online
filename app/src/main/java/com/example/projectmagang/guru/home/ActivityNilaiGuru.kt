package com.example.projectmagang.guru.home

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.adapter.ListNilaiGuruAdapter
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.model.ResponseListDataNilaiGuru
import kotlinx.android.synthetic.main.activity_nilai_guru.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityNilaiGuru : AppCompatActivity() {
    lateinit var nilaiGuruAdapter: ListNilaiGuruAdapter
    lateinit var SP : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nilai_guru)
        SP = getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        toolbar.title = "Daftar Nilai ${intent.getStringExtra("mapel")} (${intent.getStringExtra("kelas")})"
        nilaiGuruAdapter = ListNilaiGuruAdapter(applicationContext, arrayListOf())
        val rvNilai = findViewById<RecyclerView>(R.id.rvNilai)
        getNilai(SP.getString("id_user","").toString(),intent.getIntExtra("id_mapel",0))

        rvNilai.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = nilaiGuruAdapter
        }
    }

    fun getNilai(id : String, id_mapel : Int){
        UtilsAPI().apiService.nilaiGuru(id, id_mapel).enqueue(object : Callback<ResponseListDataNilaiGuru> {
            override fun onFailure(call: Call<ResponseListDataNilaiGuru>, t: Throwable) {
                t.printStackTrace()
                showMessage("gagal load data")
                loading.visibility = View.GONE
            }
            override fun onResponse(call: Call<ResponseListDataNilaiGuru>, response: Response<ResponseListDataNilaiGuru>) {
                if(response.isSuccessful) {
                    loading.visibility = View.GONE
                    val data = response.body()
                    if(data!!.response) {
                        nilaiGuruAdapter.setData(data.nilai)
                    }
                    else { kosongNilai.visibility = View.VISIBLE }
                }
            }
        })
    }

    fun showMessage(msg : String){
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}