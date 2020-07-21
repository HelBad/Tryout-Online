package com.example.projectmagang

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.guru.ActivityUtama
import com.example.projectmagang.modul.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreen : AppCompatActivity() {
    private lateinit var SP: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        SP = getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        val backgrond = object : Thread(){
            override fun run() {
                try {
                    sleep(500)
                    //tambah loading
                    getStatusLogin(SP.getString("id_user", "").toString())
                }
                catch (e: Exception) { e.printStackTrace() }
            }
        }
        backgrond.start()
    }

    private fun getStatusLogin(id: String) {
        if (id != "") {
            UtilsAPI().apiService.cekLevel(id).enqueue(object : Callback<ResponseLogin> {
                    override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                        t.printStackTrace()
                    }
                    override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                        if (response.isSuccessful) {
                            val data = response.body()
                            if (data!!.level == "G") {
                                startActivity(Intent(applicationContext,
                                    ActivityUtama::class.java))
                                finish()
                            }
                            else if (data.level == "S") {
                                startActivity(Intent(applicationContext, com.example.projectmagang.siswa.ActivityUtama::class.java))
                                finish()
                            }
                        }
                    }
                })
        }
        else{ startActivity(Intent(applicationContext, ActivityLogin::class.java)) }
    }
}