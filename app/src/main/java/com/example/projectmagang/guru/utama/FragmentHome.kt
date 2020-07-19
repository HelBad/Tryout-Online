package com.example.projectmagang.guru.utama

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectmagang.R
import com.example.projectmagang.data.KetDashboard
import com.example.projectmagang.guru.ActivityProfil
import com.example.projectmagang.guru.ActivitySoal
import com.example.projectmagang.network.ApiService
import kotlinx.android.synthetic.main.fragment_home_guru.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentHome : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home_guru, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getContent()

    }

    fun getContent(){
        ApiService.endpoint.dashboard()
            .enqueue(object :Callback<KetDashboard>{
                override fun onFailure(call: Call<KetDashboard>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<KetDashboard>,
                    response: Response<KetDashboard>
                ) {
                    if(response.isSuccessful){
                        val data = response.body()
                        jumGuru.text = data!!.jumlah_guru.toString()
                        jumKelas.text = data.jumlah_kelas.toString()
                        jumMapel.text = data.jumlah_mapel.toString()
                        jumSiswa.text = data.jumlah_siswa.toString()
                    }
                }

            })
    }
}