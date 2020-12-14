package com.example.projectmagang.guru.home.ajukanmapel

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.projectmagang.R
import com.example.projectmagang.data.DataMapelDiajukan
import com.example.projectmagang.data.ResponseListDataMapelDiajukan
import com.example.projectmagang.data.ResponseMessage
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
        mapelTersediaAdapter =
            MapelTersediaAdapter(
                applicationContext,
                arrayListOf()
            )
        getMapelTersedia()

        rvMapelTersedia.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            mapelTersediaAdapter.setOnTambahMapelCallback(object :
                MapelTersediaAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DataMapelDiajukan, position: Int) {
                    tambahMapel(data,SP.getString("iduser","").toString(), position)
                }
            })
            adapter = mapelTersediaAdapter
        }
    }

    fun tambahMapel(data: DataMapelDiajukan, id: String , position: Int){
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Konfirmasi Pengambilan Mapel")
        dialog.setMessage("Apakah anda yakin ingin mengajar ${data.nama_mapel} di kelas ${data.nama_kelas} ?")

        dialog.setPositiveButton("Iya"){dialog, which ->
            ApiService.endpoint.tambahMapelDiajukan(data.id, id).enqueue(object : Callback<ResponseMessage> {
                override fun onFailure(call: Call<ResponseMessage>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ResponseMessage>, response: Response<ResponseMessage>) {
                    if(response.isSuccessful) {
                        val data = response.body()
                        showMessage(data!!.message)
                        mapelTersediaAdapter.removeData(position)
                        if(mapelTersediaAdapter.itemCount == 0) {
                                kosongMapel.visibility = View.VISIBLE
                        }
                    }
                }
            })
            dialog.dismiss()
        }
        dialog.setNegativeButton("Batal") {dialog, which ->
            dialog.dismiss()
        }
        dialog.show()
    }

    fun getMapelTersedia() {
        ApiService.endpoint.mapelTersedia().enqueue(object : Callback<ResponseListDataMapelDiajukan> {
            override fun onFailure(call: Call<ResponseListDataMapelDiajukan>, t: Throwable) {
                t.printStackTrace()
                loading.visibility = View.GONE
            }

            override fun onResponse(call: Call<ResponseListDataMapelDiajukan>, response: Response<ResponseListDataMapelDiajukan>) {
                if(response.isSuccessful) {
                    loading.visibility = View.GONE
                    val data = response.body()
                    if(data!!.response) {
                        mapelTersediaAdapter.setData(data!!.mapel)
                    } else {
                        kosongMapel.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    fun showMessage(msg: String) {
        Toast.makeText(applicationContext,msg,Toast.LENGTH_SHORT).show()
    }
}