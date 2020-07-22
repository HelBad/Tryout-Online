package com.example.projectmagang.guru.akun

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.projectmagang.R
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.model.CekMessage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profil_guru.*
//import org.jetbrains.anko.startActivityForResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityProfil : AppCompatActivity() {
    lateinit var toolbarProfil: Toolbar
    lateinit var namaProfil : TextView
    lateinit var nipProfil : TextView
    lateinit var usernameProfil : TextView
    lateinit var emailProfil : TextView
    lateinit var tempatlahirProfil : TextView
    lateinit var tgllahirProfil : TextView
    lateinit var genderProfil : TextView
    lateinit var alamatProfil : TextView
    lateinit var telpProfil : TextView
    lateinit var gambarProfil : ImageView
    lateinit var SP : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_guru)

        toolbarProfil = findViewById(R.id.toolbarProfil)
        setSupportActionBar(toolbarProfil)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarProfil.setNavigationOnClickListener {
            finish()
        }
        val dataProfil = intent.extras
        getProfil(dataProfil)
        SP = applicationContext.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)

        btnSaveProfil.setOnClickListener {
            editProfil(SP.getString("id_user","")!!.toString(), namaProfil.text.toString(),
                genderProfil.text.toString(), tempatlahirProfil.text.toString(), tgllahirProfil.text.toString(),
                telpProfil.text.toString(), alamatProfil.text.toString(), emailProfil.text.toString(),
                usernameProfil.text.toString(), gambarProfil.toString())
//            startActivityForResult<ActivityProfil>(1)
            finish()
        }
    }

    fun getProfil(data : Bundle?){
        namaProfil = findViewById(R.id.namaProfil)
        nipProfil = findViewById(R.id.nipProfil)
        usernameProfil = findViewById(R.id.usernameProfil)
        emailProfil = findViewById(R.id.emailProfil)
        tempatlahirProfil = findViewById(R.id.tempatlahirProfil)
        tgllahirProfil = findViewById(R.id.tgllahirProfil)
        genderProfil = findViewById(R.id.genderProfil)
        alamatProfil = findViewById(R.id.alamatProfil)
        telpProfil = findViewById(R.id.telpProfil)
        gambarProfil = findViewById(R.id.gambarProfil)

        namaProfil.text = data!!.getString("nama")
        nipProfil.text = data.getString("nip")
        usernameProfil.text = data.getString("username")
        emailProfil.text = data.getString("email")
        tempatlahirProfil.text = data.getString("tempatlahir")
        tgllahirProfil.text = data.getString("tgllahir")
        genderProfil.text = data.getString("gender")
        alamatProfil.text = data.getString("alamat")
        telpProfil.text = data.getString("telp")
        Picasso.get().load(data.getString("foto")).into(gambarProfil)
    }

    fun editProfil(id: String, nama: String, jenis_kelamin: String, tempat_lahir: String, tanggal_lahir: String,
                   telp: String, alamat: String, email: String, username: String, foto: String) {
        UtilsAPI().apiService.updateProfil(id, nama, jenis_kelamin, tempat_lahir, tanggal_lahir, telp, alamat, email,
            username, foto).enqueue(object : Callback<CekMessage> {
                override fun onFailure(call: Call<CekMessage>, t: Throwable) {
                    t.printStackTrace()
                }
                override fun onResponse(call: Call<CekMessage>, response: Response<CekMessage>) {
                    if(response.isSuccessful){
                        val data = response.body()
                        Toast.makeText(applicationContext, data!!.message, Toast.LENGTH_SHORT).show()
                    }
                }

            })
    }
}