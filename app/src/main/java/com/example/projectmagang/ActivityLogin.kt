package com.example.projectmagang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import com.example.projectmagang.modul.ResponseLogin
import com.example.projectmagang.guru.utama.ActivityUtama
import com.example.projectmagang.network.ApiService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            doLogin(textUser.text.toString(), textPass.text.toString())
//            if(textUser.text.isNotEmpty()) {
//                if(textUser.text.toString() == "Guru") {
//                    val intent = Intent(this, com.example.projectmagang.guru.utama.ActivityUtama::class.java)
//                    startActivity(intent)
//                } else if(textUser.text.toString() == "Siswa") {
//                    val intent = Intent(this, com.example.projectmagang.siswa.utama.ActivityUtama::class.java)
//                    startActivity(intent)
//                } else {
//                    Toast.makeText(this, "Gagal Masuk", Toast.LENGTH_SHORT).show()
//                }
//            } else {
//                Toast.makeText(this, "Gagal Masuk", Toast.LENGTH_SHORT).show()
//            }
        }


    }

    fun doLogin(username: String, password: String){
        ApiService.endpoint.loginUser(username, password)
            .enqueue(object : Callback<ResponseLogin>{
                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    showMessage("gagal")
                }

                override fun onResponse(
                    call: Call<ResponseLogin>,
                    response: Response<ResponseLogin>
                ) {
                    if(response.isSuccessful){
                        val responseLogin: ResponseLogin? = response.body()
                        if(responseLogin!!.response){
                            if(responseLogin.level == "G"){
                                showMessage(responseLogin.message)
                                startActivity(Intent(applicationContext,ActivityUtama::class.java))
                            }else if(responseLogin.level == "S"){
                                showMessage(responseLogin.message)
                                startActivity(Intent(applicationContext,com.example.projectmagang.siswa.utama.ActivityUtama::class.java))
                            }else{
                                showMessage("Pengguna Tidak Ditemukan")
                            }

                        } else{
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