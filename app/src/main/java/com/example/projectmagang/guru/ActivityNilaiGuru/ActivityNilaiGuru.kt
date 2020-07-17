package com.example.projectmagang.guru.ActivityNilaiGuru
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.data.Nilai.DataNilai
import com.example.projectmagang.data.Nilai.ResponseListDataNilaiGuru
import com.example.projectmagang.network.ApiService
import kotlinx.android.synthetic.main.activity_nilai_guru.*
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

        nilaiGuruAdapter = NilaiGuruAdapter(
            applicationContext, arrayListOf()
        )
        val rvNilai = findViewById<RecyclerView>(R.id.rvNilai)

        getNilai(SP.getString("iduser","").toString(),intent.getIntExtra("idmapel",0))

        rvNilai.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = nilaiGuruAdapter

        }
    }

    fun getNilai(id : String, idmapel : Int){
        ApiService.endpoint.guruNilai(id, idmapel)
            .enqueue(object : Callback<ResponseListDataNilaiGuru>{
                override fun onFailure(call: Call<ResponseListDataNilaiGuru>, t: Throwable) {
                    t.printStackTrace()
                    showMessage("gagal load data")
                }

                override fun onResponse(
                    call: Call<ResponseListDataNilaiGuru>,
                    response: Response<ResponseListDataNilaiGuru>
                ) {
                    if(response.isSuccessful){
                        val data = response.body()
                        nilaiGuruAdapter.setData(data!!.nilai)
                    }
                }

            })
    }

    fun showMessage(msg : String){
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}