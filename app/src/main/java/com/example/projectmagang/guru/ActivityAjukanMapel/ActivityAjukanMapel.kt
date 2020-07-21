package com.example.projectmagang.guru.ActivityAjukanMapel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.data.Mapel.DataMapel
import com.example.projectmagang.data.Mapel.DataMapelDiajukan
import com.example.projectmagang.data.Mapel.ResponseListDataMapelDiajukan
import com.example.projectmagang.data.ResponseMessage
import com.example.projectmagang.guru.ActivityNilaiGuru.ActivityNilaiGuru
import com.example.projectmagang.guru.utama.FragmentNilai.NilaiMapelAdapter
import com.example.projectmagang.network.ApiService
import kotlinx.android.synthetic.main.activity_ajukan_mapel.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityAjukanMapel : AppCompatActivity() {

    lateinit var mapelTersediaAdapter: MapelTersediaAdapter
    lateinit var SP : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ajukan_mapel)
        SP = getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        mapelTersediaAdapter = MapelTersediaAdapter(applicationContext, arrayListOf())
        getMapelTersedia()

        rvMapelTersedia.apply {
            layoutManager = LinearLayoutManager(applicationContext)

            mapelTersediaAdapter.setOnTambahMapelCallback(object : MapelTersediaAdapter.OnItemClickCallback{

                override fun onItemClicked(data: DataMapelDiajukan, position: Int) {
                    tambahMapel(data.id,SP.getString("iduser","").toString(), position)
                }
            })
            adapter = mapelTersediaAdapter
        }
    }

    fun tambahMapel(id_mapel : Int, id : String , position : Int){
        ApiService.endpoint.tambahMapelDiajukan(id_mapel, id)
            .enqueue(object : Callback<ResponseMessage>{
                override fun onFailure(call: Call<ResponseMessage>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<ResponseMessage>,
                    response: Response<ResponseMessage>
                ) {
                    if(response.isSuccessful){
                        val data = response.body()
                        showMessage(data!!.message)
                        mapelTersediaAdapter.removeData(position)
                    }
                }

            })
    }

    fun getMapelTersedia(){
        ApiService.endpoint.mapelTersedia()
            .enqueue(object : Callback<ResponseListDataMapelDiajukan>{
                override fun onFailure(call: Call<ResponseListDataMapelDiajukan>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<ResponseListDataMapelDiajukan>,
                    response: Response<ResponseListDataMapelDiajukan>
                ) {
                    if(response.isSuccessful){
                        val data = response.body()
                        if(data!!.response){
                            mapelTersediaAdapter.setData(data!!.mapel)
                        }
                    }
                }

            })
    }

    fun showMessage(msg : String){
        Toast.makeText(applicationContext,msg,Toast.LENGTH_SHORT).show()
    }

    fun getMapelDiajukan(){

    }
}