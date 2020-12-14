package com.example.projectmagang.siswa.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.data.DataJadwalSiswa
import com.example.projectmagang.data.ResponseListDataJadwalSiswa
import com.example.projectmagang.network.ApiService
import kotlinx.android.synthetic.main.fragment_home_siswa.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentHome : Fragment() {
    lateinit var recyclerHomeS : RecyclerView
    lateinit var loading : ProgressBar
    lateinit var jadwalAdapter: JadwalSiswaAdapter
    lateinit var SP : SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home_siswa, container, false)

        SP = requireActivity().getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        getJadwal(SP.getString("iduser","").toString())
        recyclerHomeS = view.findViewById(R.id.recyclerHomeS)
        loading = view.findViewById(R.id.loading)
        jadwalAdapter = JadwalSiswaAdapter(requireActivity().applicationContext, arrayListOf())

        recyclerHomeS.apply {
            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            jadwalAdapter.setOnMengerjakanCallback(object :
                JadwalSiswaAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DataJadwalSiswa, durasi : Int) {
                    val intent = Intent(activity!!.applicationContext, ActivityUjian::class.java)
                    intent.putExtra("id_mapel", data.id_mapel)
                    intent.putExtra("durasi", durasi)
                    startActivity(intent)
                }
            })
            adapter = jadwalAdapter
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        getJadwal(SP.getString("iduser","").toString())
    }

    fun getJadwal(id: String) {
        ApiService.endpoint.listJadwal(id).enqueue(object : Callback<ResponseListDataJadwalSiswa> {
            override fun onFailure(call: Call<ResponseListDataJadwalSiswa>, t: Throwable) {
                t.printStackTrace()
                loading.visibility = View.GONE
            }

            override fun onResponse(call: Call<ResponseListDataJadwalSiswa>, response: Response<ResponseListDataJadwalSiswa>) {
                if(response.isSuccessful) {
                    loading.visibility = View.GONE
                    val data = response.body()
                    if(data!!.response) {
                        jadwalAdapter.setData(data.jadwal)
                    } else {
                        kosongJadwalSiswa.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}