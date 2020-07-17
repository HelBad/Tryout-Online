package com.example.projectmagang.guru.utama.FragmentNilai

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
import com.example.projectmagang.data.Mapel.DataMapel
import com.example.projectmagang.data.Mapel.ResponseListDataMapel
import com.example.projectmagang.guru.ActivityNilaiGuru.ActivityNilaiGuru
import com.example.projectmagang.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentNilai : Fragment() {
    lateinit var rvMapel : RecyclerView
    lateinit var nilaiMapelAdapter: NilaiMapelAdapter
    lateinit var SP : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_nilai_guru, container, false)
        SP = activity!!.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        rvMapel = view.findViewById(R.id.recyclerNilai)
        nilaiMapelAdapter =
            NilaiMapelAdapter(
                activity!!.applicationContext,
                arrayListOf()
            )

        getMapel(SP.getString("iduser","").toString())

        rvMapel.apply {
            layoutManager = LinearLayoutManager(activity!!.applicationContext)

            nilaiMapelAdapter.setOnDetailCallback(object : NilaiMapelAdapter.OnItemClickCallback{
                override fun onItemClicked(data: DataMapel) {
                    val intent = Intent(activity!!.applicationContext, ActivityNilaiGuru::class.java)
                    intent.putExtra("idmapel", data.id)
                    startActivity(intent)
                }
            })
            adapter = nilaiMapelAdapter

        }
        return view
    }

    fun getMapel(id : String){
        ApiService.endpoint.mapelGuru(id)
            .enqueue(object :Callback<ResponseListDataMapel>{
                override fun onFailure(call: Call<ResponseListDataMapel>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<ResponseListDataMapel>,
                    responseList: Response<ResponseListDataMapel>
                ) {
                    if(responseList.isSuccessful){
                        showMessage("Data berhasil di load")
                        val data = responseList.body()
                        val dataMapel : List<DataMapel> = data!!.mapel

                        nilaiMapelAdapter.setData(dataMapel)
                    }
                }

            })
    }


    fun showMessage(msg : String){
        Toast.makeText(activity!!.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}