package com.example.projectmagang.guru.utama.FragmentSiswa

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.data.Mapel.DataMapel
import com.example.projectmagang.data.Mapel.ResponseListDataMapel
import com.example.projectmagang.guru.ActivitySiswaGuru.ActivitySiswaGuru
import com.example.projectmagang.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentSiswa : Fragment() {
    lateinit var rvMapel : RecyclerView
    lateinit var siswaMapelAdapter: SiswaMapelAdapter
    lateinit var SP : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_siswa_guru, container, false)
        SP = activity!!.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        rvMapel = view.findViewById(R.id.recyclerSiswa)
        siswaMapelAdapter = SiswaMapelAdapter(activity!!.applicationContext, arrayListOf())

        getMapel(SP.getString("iduser","").toString())
        rvMapel.apply {
            layoutManager = LinearLayoutManager(activity!!.applicationContext)
            siswaMapelAdapter.setOnDetailCallback(object : SiswaMapelAdapter.OnItemClickCallback{
                override fun onItemClicked(data: DataMapel) {
                    val intent = Intent(activity!!.applicationContext, ActivitySiswaGuru::class.java)
                    intent.putExtra("idmapel", data.id)
                    intent.putExtra("kelas", data.nama_kelas)
                    intent.putExtra("mapel", data.nama_mapel)
                    startActivity(intent)
                }

            })

            adapter = siswaMapelAdapter
        }


        return view
    }

    fun getMapel(id : String){
        ApiService.endpoint.mapelGuru(id)
            .enqueue(object : Callback<ResponseListDataMapel> {
                override fun onFailure(call: Call<ResponseListDataMapel>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<ResponseListDataMapel>,
                    responseList: Response<ResponseListDataMapel>
                ) {
                    if(responseList.isSuccessful){
                        val data = responseList.body()
                        if(data!!.response){
                            siswaMapelAdapter.setData(data.mapel)
                        }
                    }
                }

            })
    }


}