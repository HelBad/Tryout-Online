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
import com.example.projectmagang.guru.utama.ActivityUtama
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

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        SP = getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
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
                                setSPGuru(
                                    responseLogin.level,
                                    responseLogin.data!!.nama.toString(),
                                    responseLogin.data.nip!!.toInt(),
                                    responseLogin.data.jenkel.toString(),
                                    responseLogin.data.email.toString(),
                                    responseLogin.data.username.toString(),
                                    responseLogin.data.telp!!.toInt(),
                                    responseLogin.data.tempat_lahir.toString(),
                                    responseLogin.data.tanggal_lahir.toString(),
                                    responseLogin.data.alamat.toString()
                                    )
                                startActivity(Intent(applicationContext,ActivityUtama::class.java))
                            }else if(responseLogin.level == "S"){
                                showMessage(responseLogin.message)
//                                setSPSiswa(
//                                    responseLogin.level,
//                                    responseLogin.data!!.nama.toString(),
//                                    123,
////                                    responseLogin.data.nisn!!.toInt(),
//                                    responseLogin.data.jenkel.toString(),
//                                    responseLogin.data.email.toString(),
//                                    responseLogin.data.username.toString(),
//                                    responseLogin.data.telp!!.toInt(),
//                                    responseLogin.data.tempat_lahir.toString(),
//                                    responseLogin.data.tanggal_lahir.toString(),
//                                    responseLogin.data.alamat.toString()
//                                )
                                startActivity(Intent(applicationContext,com.example.projectmagang.siswa.utama.ActivityUtama::class.java))
                            }else{
                                showMessage("Pengguna Tidak Ditemukan")
                            }

                        }else{
                            showMessage(responseLogin.message)
                        }

                    }
                }

            })
    }

    fun setSPGuru(level : String,
                  nama: String,
                  nip: Int,
                  jenkel : String,
                  email : String,
                  username: String,
                  telp : Int,
                  tempatlahir : String,
                  tanggallahir : String,
                  alamat : String){

        val editor = SP.edit()
        editor.putString("level",level)
        editor.putString("nama", nama)
        editor.putInt("nip", nip)
        editor.putString("jenkel", jenkel)
        editor.putString("email", email)
        editor.putString("username", username)
        editor.putInt("telp", telp)
        editor.putString("tempatLahir", tempatlahir)
        editor.putString("tanggalLahir", tanggallahir)
        editor.putString("alamat", alamat)
        editor.apply()
    }

    fun setSPSiswa(level : String,
                   nama: String,
                   nisn: Int,
                   jenkel : String,
                   email : String,
                   username: String,
                   telp : Int,
                   tempatlahir : String,
                   tanggallahir : String,
                   alamat : String){
        val editor = SP.edit()
        editor.putString("level",level)
        editor.putString("nama", nama)
        editor.putInt("nisn", nisn)
        editor.putString("jenkel", jenkel)
        editor.putString("email", email)
        editor.putString("username", username)
        editor.putInt("telp", telp)
        editor.putString("tempatLahir", tempatlahir)
        editor.putString("tanggalLahir", tanggallahir)
        editor.putString("alamat", alamat)
        editor.apply()
    }
    fun showMessage(message : String){
         Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}