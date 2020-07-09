package com.example.projectmagang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class ActivityLogin : AppCompatActivity() {
    lateinit var textUser: EditText
    lateinit var textPass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        textUser = findViewById(R.id.textUser)
        textPass = findViewById(R.id.textPass)

        btnSignin.setOnClickListener {
            if(textUser.text.isNotEmpty()) {
                if(textUser.text.toString() == "Guru") {
                    val intent = Intent(this, ActivityUtama::class.java)
                    startActivity(intent)
                } else if(textUser.text.toString() == "Siswa") {
                    val intent = Intent(this, ActivityUtama::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Gagal Masuk", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Gagal Masuk", Toast.LENGTH_SHORT).show()
            }
        }
    }
}