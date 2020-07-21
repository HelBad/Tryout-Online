package com.example.projectmagang.guru

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.projectmagang.R
import com.example.projectmagang.data.ResponseMessage
import com.example.projectmagang.network.ApiService
import kotlinx.android.synthetic.main.activity_profil_guru.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityProfil : AppCompatActivity() {
    lateinit var toolbarProfil: Toolbar
    lateinit var SP : SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_guru)
        //init
        toolbarProfil = findViewById(R.id.toolbarProfil)
        val dataProfil = intent.extras
        SP = applicationContext.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)

        //endinit

        getProfil(dataProfil)
        setSupportActionBar(toolbarProfil)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        btnSaveProfil.setOnClickListener {
            editProfil(SP.getString("iduser","")!!.toString(), namaProfil.text.toString(), alamatProfil.text.toString(), 81539969070)
            finish()
        }
        toolbarProfil.setNavigationOnClickListener {
            finish()
        }
    }

    fun getProfil(data : Bundle?){
        namaProfil.setText(data!!.getString("nama"))
        usernameProfil.text = data.getString("username")
        nipProfil.text = data.getString("nip")
        genderProfil.text = data.getString("jenkel")
        alamatProfil.setText(data.getString("alamat"))
        telpProfil.setText(data.getString("telp"))
    }

    fun editProfil(id: String, nama: String, alamat: String, telp: Long){
        ApiService.endpoint.editProfil(id,nama,alamat,telp)
            .enqueue(object : Callback<ResponseMessage>{
                override fun onFailure(call: Call<ResponseMessage>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ResponseMessage>, response: Response<ResponseMessage>) {
                    if(response.isSuccessful){
                        val data = response.body()
                        Toast.makeText(applicationContext, data!!.message, Toast.LENGTH_SHORT).show()
                    }
                }

            })
    }


}