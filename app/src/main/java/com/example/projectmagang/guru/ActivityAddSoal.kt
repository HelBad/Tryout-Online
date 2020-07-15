package com.example.projectmagang.guru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.example.projectmagang.R

class ActivityAddSoal : AppCompatActivity() {
    lateinit var toolbarAddSoal: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addsoal_guru)

        toolbarAddSoal = findViewById(R.id.toolbarAddSoal)
        setSupportActionBar(toolbarAddSoal)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarAddSoal.setNavigationOnClickListener {
            finish()
        }
    }
}