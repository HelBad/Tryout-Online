package com.example.projectmagang.guru.nilai

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.data.DataMapel
import com.example.projectmagang.data.ResponseListDataMapel
import com.example.projectmagang.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentNilai : Fragment() {
    lateinit var loading : ProgressBar
    lateinit var kosongMapel : RelativeLayout
    lateinit var rvMapel : RecyclerView
    lateinit var nilaiMapelAdapter: NilaiMapelAdapter
    lateinit var SP : SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.fragment_nilai_guru, container, false)
        loading = view.findViewById(R.id.loading)
        kosongMapel = view.findViewById(R.id.kosongMapel)
        SP = requireActivity().getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        rvMapel = view.findViewById(R.id.recyclerNilai)
        nilaiMapelAdapter =
            NilaiMapelAdapter(
                requireActivity().applicationContext,
                arrayListOf()
            )
        getMapel(SP.getString("iduser","").toString())

        rvMapel.apply {
            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            nilaiMapelAdapter.setOnDetailCallback(object :
                NilaiMapelAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DataMapel) {
                    val intent = Intent(activity!!.applicationContext, ActivityNilaiGuru::class.java)
                    intent.putExtra("idmapel", data.id)
                    intent.putExtra("kelas", data.nama_kelas)
                    intent.putExtra("mapel", data.nama_mapel)
                    startActivity(intent)
                }
            })
            adapter = nilaiMapelAdapter
        }
        return view
    }

    fun getMapel(id : String) {
        ApiService.endpoint.mapelGuru(id).enqueue(object :Callback<ResponseListDataMapel> {
            override fun onFailure(call: Call<ResponseListDataMapel>, t: Throwable) {
                t.printStackTrace()
                loading.visibility = View.GONE
            }

            override fun onResponse(call: Call<ResponseListDataMapel>, responseList: Response<ResponseListDataMapel>) {
                if(responseList.isSuccessful) {
                    loading.visibility = View.GONE
                    val data = responseList.body()
                    if(data!!.response) {
                        nilaiMapelAdapter.setData(data.mapel)
                    } else {
                        kosongMapel.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}