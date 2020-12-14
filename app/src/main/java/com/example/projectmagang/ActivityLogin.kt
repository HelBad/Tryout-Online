package com.example.projectmagang

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import com.example.projectmagang.data.ResponseLogin
import com.example.projectmagang.guru.ActivityUtama
import com.example.projectmagang.network.ApiService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityLogin : AppCompatActivity() {
    lateinit var textUser: EditText
    lateinit var textPass: EditText
    lateinit var SP : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        SP = getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        textUser = findViewById(R.id.textUser)
        textPass = findViewById(R.id.textPass)

        btnSignin.setOnClickListener {
            doLogin(textUser.text.toString(), textPass.text.toString())
        }
    }

    fun doLogin(username: String, password: String){
        ApiService.endpoint.loginUser(username, password).enqueue(object : Callback<ResponseLogin>{
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                showMessage("gagal")
            }

            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if(response.isSuccessful) {
                    val responseLogin: ResponseLogin? = response.body()
                    if(responseLogin!!.response) {
                        if(responseLogin.level == "G") {
                            showMessage(responseLogin.message)
                            val editor = SP.edit()
                            editor.putString("iduser", responseLogin.id)
                            editor.apply()
                            startActivity(Intent(applicationContext,
                                ActivityUtama::class.java))
                            finish()
                        } else if(responseLogin.level == "S") {
                            showMessage(responseLogin.message)
                            val editor = SP.edit()
                            editor.putString("iduser", responseLogin.id)
                            editor.apply()
                            startActivity(Intent(applicationContext,
                                com.example.projectmagang.siswa.ActivityUtama::class.java))
                            finish()
                        } else {
                            showMessage("Pengguna Tidak Ditemukan")
                        }
                    } else {
                        showMessage(responseLogin.message)
                    }
                }
            }
        })
    }

    fun showMessage(message : String){
         Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}