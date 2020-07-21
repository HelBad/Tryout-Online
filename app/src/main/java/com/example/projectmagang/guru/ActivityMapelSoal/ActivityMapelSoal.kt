package com.example.projectmagang.guru.ActivityMapelSoal

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmagang.R
import com.example.projectmagang.data.Mapel.ResponseListDataMapel
import com.example.projectmagang.guru.ActivityAjukanMapel.MapelTersediaAdapter
import com.example.projectmagang.network.ApiService
import kotlinx.android.synthetic.main.activity_mapel_soal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityMapelSoal : AppCompatActivity() {
    lateinit var mapelSoalAdapter: MapelSoalAdapter
    lateinit var SP : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapel_soal)
        SP = getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        mapelSoalAdapter = MapelSoalAdapter(applicationContext, arrayListOf())

        getMapelGuru(SP.getString("iduser","").toString())
        rvMapel.apply {
            layoutManager = LinearLayoutManager(applicationContext)

            adapter = mapelSoalAdapter
        }


    }

    fun getMapelGuru(id : String){
        ApiService.endpoint.mapelGuru(id)
            .enqueue(object : Callback<ResponseListDataMapel>{
                override fun onFailure(call: Call<ResponseListDataMapel>, t: Throwable) {
                    t.printStackTrace()
                    showMessage("Server Error")
                }

                override fun onResponse(
                    call: Call<ResponseListDataMapel>,
                    response: Response<ResponseListDataMapel>
                ) {
                    if(response.isSuccessful){
                        val data = response.body()
                        mapelSoalAdapter.setData(data!!.mapel)
                    }
                }

            })
    }

    fun showMessage(msg : String){
        Toast.makeText(applicationContext,msg,Toast.LENGTH_SHORT).show()
    }
}