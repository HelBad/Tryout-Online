package com.example.projectmagang.siswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.projectmagang.R
import com.example.projectmagang.api.BaseApiService
import com.squareup.picasso.Picasso

class ActivityProfil : AppCompatActivity() {
    lateinit var toolbarProfilS: Toolbar
    lateinit var mApiService: BaseApiService
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_siswa)

        toolbarProfilS = findViewById(R.id.toolbarProfilS)
        setSupportActionBar(toolbarProfilS)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarProfilS.setNavigationOnClickListener {
            finish()
        }

        nisnProfilS = findViewById(R.id.nisnProfilS)
        namaProfilS = findViewById(R.id.namaProfilS)
        usernameProfilS = findViewById(R.id.usernameProfilS)
        emailProfilS = findViewById(R.id.emailProfilS)
        kelasProfilS = findViewById(R.id.kelasProfilS)
        jurusanProfilS = findViewById(R.id.jurusanProfilS)
        tempatlahirProfilS = findViewById(R.id.tempatlahirProfilS)
        tgllahirProfilS = findViewById(R.id.tgllahirProfilS)
        genderProfilS = findViewById(R.id.genderProfilS)
        alamatProfilS = findViewById(R.id.alamatProfilS)
        telpProfilS = findViewById(R.id.telpProfilS)
        gambarProfilS = findViewById(R.id.gambarProfilS)

        nisnProfilS.text = intent.getStringExtra("nisn")
        namaProfilS.text = intent.getStringExtra("nama")
        usernameProfilS.text = intent.getStringExtra("username")
        emailProfilS.text = intent.getStringExtra("email")
//        kelasProfilS.text = intent.getStringExtra("id_mapel")
//        jurusanProfilS.text = intent.getStringExtra("id_user")
        tempatlahirProfilS.text = intent.getStringExtra("tempatlahir")
        tgllahirProfilS.text = intent.getStringExtra("tgllahir")
        genderProfilS.text = intent.getStringExtra("gender")
        alamatProfilS.text = intent.getStringExtra("alamat")
        telpProfilS.text = intent.getStringExtra("telp")
        Picasso.get().load(intent.getStringExtra("foto")).into(gambarProfilS)
    }
}