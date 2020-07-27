package com.example.projectmagang.siswa.utama.FragmentHome

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
import com.example.projectmagang.data.Jadwal.DataJadwalSiswa
import com.example.projectmagang.data.Jadwal.ResponseListDataJadwalSiswa
import com.example.projectmagang.data.ResponseMessage
import com.example.projectmagang.network.ApiService
import com.example.projectmagang.siswa.ActivityUjian.ActivityUjian
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentHome : Fragment() {
    lateinit var recyclerHomeS : RecyclerView
    lateinit var jadwalAdapter: JadwalSiswaAdapter
    lateinit var SP : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home_siswa, container, false)

        SP = activity!!.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        getJadwal(SP.getString("iduser","").toString())
        recyclerHomeS = rootView.findViewById(R.id.recyclerHomeS)
        jadwalAdapter = JadwalSiswaAdapter(activity!!.applicationContext, arrayListOf())

        recyclerHomeS.apply {
            layoutManager = LinearLayoutManager(activity!!.applicationContext)
            jadwalAdapter.setOnDetailCallback(object : JadwalSiswaAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DataJadwalSiswa) {
                    val intent = Intent(activity!!.applicationContext, ActivityUjian::class.java)
                    intent.putExtra("id_mapel", data.id_mapel)
                    intent.putExtra("waktu", data.waktu)
                    startActivity(intent)
                }
            })
            adapter = jadwalAdapter
        }
        return rootView
    }

    fun getJadwal(id : String){
        ApiService.endpoint.listJadwal(id).enqueue(object : Callback<ResponseListDataJadwalSiswa> {
            override fun onFailure(call: Call<ResponseListDataJadwalSiswa>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<ResponseListDataJadwalSiswa>, response: Response<ResponseListDataJadwalSiswa>) {
                if(response.isSuccessful) {
                    val data = response.body()
                    val dataJadwal : List<DataJadwalSiswa> = data!!.jadwal
                    jadwalAdapter.setData(dataJadwal)
                }
            }
        })
    }
}