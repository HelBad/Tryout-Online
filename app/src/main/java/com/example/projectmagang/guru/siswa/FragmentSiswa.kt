package com.example.projectmagang.guru.siswa

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.adapter.SiswaAdapter
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.modul.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentSiswa : Fragment() {
    lateinit var recyclerSiswa: RecyclerView
    lateinit var listSiswaAdapter: SiswaAdapter
    lateinit var SP : SharedPreferences
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_siswa_guru, container, false)

        SP = activity!!.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        getSiswa(SP.getString("id_user","").toString())
        recyclerSiswa = rootView.findViewById(R.id.recyclerSiswa)
        listSiswaAdapter = SiswaAdapter (activity!!.applicationContext, arrayListOf())

        recyclerSiswa.apply {
            layoutManager = LinearLayoutManager(activity!!.applicationContext)
            listSiswaAdapter.setOnDetailCallback(object : SiswaAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DataSiswa) {
                    val intent = Intent(activity!!.applicationContext, ActivityDetailSiswa::class.java)
//                    intent.putExtra("nama_guru", data.nama_guru)
                    startActivity(intent)
                }
            })
            adapter = listSiswaAdapter
        }
        return rootView
    }

    fun getSiswa(id : String){
        UtilsAPI().apiService.listSiswa(id).enqueue(object :Callback<ResponseDataSiswa> {
            override fun onFailure(call: Call<ResponseDataSiswa>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<ResponseDataSiswa>, response: Response<ResponseDataSiswa>) {
                if(response.isSuccessful) {
                    val data = response.body()
                    val dataSiswa : List<DataSiswa> = data!!.siswa
                    listSiswaAdapter.setData(dataSiswa)
                }
            }
        })
    }
}