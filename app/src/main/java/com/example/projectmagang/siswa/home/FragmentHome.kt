package com.example.projectmagang.siswa.home

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
import com.example.projectmagang.adapter.JadwalAdapter
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.modul.DataJadwal
import com.example.projectmagang.modul.ResponseDataJadwal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentHome : Fragment() {
    lateinit var recyclerHomeS : RecyclerView
    lateinit var jadwalAdapter: JadwalAdapter
    lateinit var SP : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home_siswa, container, false)

        SP = activity!!.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        getJadwal(SP.getString("id_user","").toString())
        recyclerHomeS = rootView.findViewById(R.id.recyclerHomeS)
        jadwalAdapter = JadwalAdapter(activity!!.applicationContext, arrayListOf())

        recyclerHomeS.apply {
            layoutManager = LinearLayoutManager(activity!!.applicationContext)
            jadwalAdapter.setOnDetailCallback(object : JadwalAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DataJadwal) {
                    val intent = Intent(activity!!.applicationContext, ActivityUjian::class.java)
//                    intent.putExtra("nama_guru", data.nama_guru)
                    startActivity(intent)
                }
            })
            adapter = jadwalAdapter
        }
        return rootView
    }

    fun getJadwal(id : String){
        UtilsAPI().apiService.listJadwal(id).enqueue(object : Callback<ResponseDataJadwal> {
            override fun onFailure(call: Call<ResponseDataJadwal>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<ResponseDataJadwal>, response: Response<ResponseDataJadwal>) {
                if(response.isSuccessful) {
                    val data = response.body()
                    val dataJadwal : List<DataJadwal> = data!!.jadwal
                    jadwalAdapter.setData(dataJadwal)
                }
            }
        })
    }
}