package com.example.projectmagang.guru.nilai

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
import com.example.projectmagang.adapter.MapelAdapter
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.modul.DataMapel
import com.example.projectmagang.modul.ResponseDataMapel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentNilai : Fragment() {
    lateinit var rvMapel : RecyclerView
    lateinit var nilaiMapelAdapter: MapelAdapter
    lateinit var SP : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_nilai_guru, container, false)

        SP = activity!!.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        getMapel(SP.getString("id_user","").toString())
        rvMapel = view.findViewById(R.id.recyclerNilai)
        nilaiMapelAdapter = MapelAdapter(activity!!.applicationContext, arrayListOf())

        rvMapel.apply {
            layoutManager = LinearLayoutManager(activity!!.applicationContext)
            nilaiMapelAdapter.setOnDetailCallback(object : MapelAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DataMapel) {
                    val intent = Intent(activity!!.applicationContext, ActivityNilai::class.java)
                    intent.putExtra("nama_guru", data.nama_guru)
                    startActivity(intent)
                }
            })
            adapter = nilaiMapelAdapter
        }
        return view
    }

    fun getMapel(id : String){
        UtilsAPI().apiService.mapelGuru(id).enqueue(object :Callback<ResponseDataMapel> {
            override fun onFailure(call: Call<ResponseDataMapel>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<ResponseDataMapel>, responseList: Response<ResponseDataMapel>) {
                if(responseList.isSuccessful) {
                    val data = responseList.body()
                    val dataMapel : List<DataMapel> = data!!.data
                    nilaiMapelAdapter.setData(dataMapel)
                }
            }
        })
    }
}