package com.example.projectmagang.siswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.projectmagang.R

class ActivityProfil : AppCompatActivity() {
    lateinit var toolbarProfilS: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_siswa)

        toolbarProfilS = findViewById(R.id.toolbarProfilS)
        setSupportActionBar(toolbarProfilS)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarProfilS.setNavigationOnClickListener {
            finish()
        }
    }
}