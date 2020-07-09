package com.example.projectmagang.guru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.projectmagang.R

class ActivityProfil : AppCompatActivity() {
    lateinit var toolbarProfil: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_guru)

        toolbarProfil = findViewById(R.id.toolbarProfil)
        setSupportActionBar(toolbarProfil)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarProfil.setNavigationOnClickListener {
            finish()
        }
    }
}