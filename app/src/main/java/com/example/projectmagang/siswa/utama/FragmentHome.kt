package com.example.projectmagang.siswa.utama

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.projectmagang.R
import com.example.projectmagang.data.ResponseMessage
import com.example.projectmagang.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class FragmentHome : Fragment() {

    lateinit var SP : SharedPreferences
    lateinit var jawab : ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_siswa, container, false)
        SP = activity!!.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)

        jawab.add(0,"4,B")
        jawab.add(2,"5,C")

        ApiService.endpoint.siswaJawab(SP.getString("iduser","").toString(),19,jawab)
            .enqueue(object : Callback<ResponseMessage>{
                override fun onFailure(call: Call<ResponseMessage>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<ResponseMessage>,
                    response: Response<ResponseMessage>
                ) {
                    if(response.isSuccessful){
                        val data = response.body()
                        Toast.makeText(activity!!.applicationContext,data!!.message,Toast.LENGTH_SHORT).show()
                    }
                }

            })
        return view
    }


}