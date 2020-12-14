package com.example.projectmagang.siswa.nilai

import android.content.Context
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
import com.example.projectmagang.data.ResponseListDataNilai
import com.example.projectmagang.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentNilai : Fragment() {
    lateinit var loading : ProgressBar
    lateinit var kosongNilai : RelativeLayout
    lateinit var recyclerNilaiS : RecyclerView
    lateinit var nilaiSiswaAdapter: NilaiSiswaAdapter
    lateinit var SP: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_nilai_siswa, container, false)

        SP = requireActivity().getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        getNilai(SP.getString("iduser","").toString())
        recyclerNilaiS = view.findViewById(R.id.recyclerNilaiS)
        loading = view.findViewById(R.id.loading)
        kosongNilai = view.findViewById(R.id.kosongNilai)
        nilaiSiswaAdapter =
            NilaiSiswaAdapter(
                requireActivity().applicationContext,
                arrayListOf()
            )

        recyclerNilaiS.apply {
            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
            adapter = nilaiSiswaAdapter
        }
        return view
    }

    fun getNilai(id: String) {
        ApiService.endpoint.siswaNilai(id).enqueue(object: Callback<ResponseListDataNilai> {
            override fun onFailure(call: Call<ResponseListDataNilai>, t: Throwable) {
                t.printStackTrace()
                loading.visibility = View.GONE
            }

            override fun onResponse(call: Call<ResponseListDataNilai>, response: Response<ResponseListDataNilai>) {
                if(response.isSuccessful) {
                    loading.visibility = View.GONE
                    val data = response.body()
                    if(data!!.response) {
                        nilaiSiswaAdapter.setData(data.nilai)
                    } else {
                        kosongNilai.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}