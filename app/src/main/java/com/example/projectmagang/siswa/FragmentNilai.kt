package com.example.projectmagang.siswa

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.adapter.NilaiSiswaAdapter
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentNilai : Fragment() {
    lateinit var recyclerNilaiS : RecyclerView
    lateinit var nilaiSiswaAdapter: NilaiSiswaAdapter
    lateinit var SP : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootview = inflater.inflate(R.layout.fragment_nilai_siswa, container, false)

        SP = activity!!.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        getNilai(SP.getString("id_user","").toString())
        recyclerNilaiS = rootview.findViewById(R.id.recyclerNilaiS)
        nilaiSiswaAdapter = NilaiSiswaAdapter(activity!!.applicationContext, arrayListOf())

        recyclerNilaiS.apply {
            layoutManager = LinearLayoutManager(activity!!.applicationContext)
            adapter = nilaiSiswaAdapter
        }
        return rootview
    }

    fun getNilai(id : String){
        UtilsAPI().apiService.siswaNilai(id).enqueue(object : Callback<ResponseDataNilaiSiswa> {
            override fun onFailure(call: Call<ResponseDataNilaiSiswa>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<ResponseDataNilaiSiswa>, response: Response<ResponseDataNilaiSiswa>) {
                if(response.isSuccessful) {
                    val data = response.body()
                    val dataNilaiSiswa : List<DataNilaiSiswa> = data!!.data
                    nilaiSiswaAdapter.setData(dataNilaiSiswa)
                }
            }
        })
    }
}