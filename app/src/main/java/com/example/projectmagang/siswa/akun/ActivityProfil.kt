package com.example.projectmagang.siswa.akun

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.projectmagang.R
import com.example.projectmagang.api.BaseApiService
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.modul.CekMessage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profil_guru.*
import kotlinx.android.synthetic.main.activity_profil_siswa.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityProfil : AppCompatActivity() {
    lateinit var toolbarProfilS: Toolbar
    lateinit var nisnProfilS: TextView
    lateinit var namaProfilS: TextView
    lateinit var usernameProfilS: TextView
    lateinit var emailProfilS: TextView
    lateinit var kelasProfilS: TextView
    lateinit var jurusanProfilS: TextView
    lateinit var tempatlahirProfilS: TextView
    lateinit var tgllahirProfilS: TextView
    lateinit var genderProfilS: TextView
    lateinit var alamatProfilS: TextView
    lateinit var telpProfilS: TextView
    lateinit var gambarProfilS : ImageView
    lateinit var SP : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_siswa)

        toolbarProfilS = findViewById(R.id.toolbarProfilS)
        setSupportActionBar(toolbarProfilS)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarProfilS.setNavigationOnClickListener {
            finish()
        }

        val dataProfil = intent.extras
        getProfil(dataProfil)
        SP = applicationContext.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)

        btnSaveProfilS.setOnClickListener {
            editProfil(SP.getString("id_user","")!!.toString(), namaProfilS.text.toString(),
                genderProfilS.text.toString(), tempatlahirProfilS.text.toString(), tgllahirProfilS.text.toString(),
                telpProfilS.text.toString(), alamatProfilS.text.toString(), emailProfilS.text.toString(),
                usernameProfilS.text.toString(), gambarProfilS.toString())
            finish()
        }
    }

    fun getProfil(data : Bundle?){
        nisnProfilS = findViewById(R.id.nisnProfilS)
        namaProfilS = findViewById(R.id.namaProfilS)
        usernameProfilS = findViewById(R.id.usernameProfilS)
        emailProfilS = findViewById(R.id.emailProfilS)
//        kelasProfilS = findViewById(R.id.kelasProfilS)
//        jurusanProfilS = findViewById(R.id.jurusanProfilS)
        tempatlahirProfilS = findViewById(R.id.tempatlahirProfilS)
        tgllahirProfilS = findViewById(R.id.tgllahirProfilS)
        genderProfilS = findViewById(R.id.genderProfilS)
        alamatProfilS = findViewById(R.id.alamatProfilS)
        telpProfilS = findViewById(R.id.telpProfilS)
        gambarProfilS = findViewById(R.id.gambarProfilS)

        nisnProfilS.text = data!!.getString("nisn")
        namaProfilS.text = data.getString("nama")
        usernameProfilS.text = data.getString("username")
        emailProfilS.text = data.getString("email")
//        kelasProfilS.text = data.getString("id_mapel")
//        jurusanProfilS.text = data.getString("id_user")
        tempatlahirProfilS.text = data.getString("tempatlahir")
        tgllahirProfilS.text = data.getString("tgllahir")
        genderProfilS.text = data.getString("gender")
        alamatProfilS.text = data.getString("alamat")
        telpProfilS.text = data.getString("telp")
        Picasso.get().load(data.getString("foto")).into(gambarProfilS)
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