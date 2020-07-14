package com.example.projectmagang.guru

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.projectmagang.R

class ActivityProfil : AppCompatActivity() {
    lateinit var toolbarProfil: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_guru)
        //init
        toolbarProfil = findViewById(R.id.toolbarProfil)
        //endinit
        setSupportActionBar(toolbarProfil)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarProfil.setNavigationOnClickListener {
            finish()
        }
    }
}