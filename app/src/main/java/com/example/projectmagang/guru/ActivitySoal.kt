package com.example.projectmagang.guru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.projectmagang.R

class ActivitySoal : AppCompatActivity() {
    lateinit var toolbarSoal: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soal_guru)

        toolbarSoal = findViewById(R.id.toolbarSoal)
        setSupportActionBar(toolbarSoal)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}