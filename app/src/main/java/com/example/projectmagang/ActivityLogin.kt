package com.example.projectmagang

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.model.ResponseLogin
import com.example.projectmagang.guru.ActivityUtama
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActivityLogin : AppCompatActivity() {
    lateinit var textUser: EditText
    lateinit var textPass: EditText
    lateinit var SP : SharedPreferences
    lateinit var alertDialog: AlertDialog.Builder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        alertDialog = AlertDialog.Builder(this)
        SP = getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        textUser = findViewById(R.id.textUser)
        textPass = findViewById(R.id.textPass)
        btnSignin.setOnClickListener {
            doLogin(textUser.text.toString(), textPass.text.toString())
        }
    }

    fun doLogin(username: String, password: String){
        UtilsAPI().apiService.loginUser(username, password).enqueue(object : Callback<ResponseLogin> {
            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                showMessage("gagal")
            }
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if(response.isSuccessful){
                    val responseLogin: ResponseLogin? = response.body()
                    if(responseLogin!!.response) {
                        if(responseLogin.level == "G"){
                            showMessage(responseLogin.message)
                            val editor = SP.edit()
                            editor.putString("id_user", responseLogin.id)
                            editor.apply()
                            startActivity(Intent(applicationContext,
                                ActivityUtama::class.java))
                            finish()
                        } else if(responseLogin.level == "S"){
                            showMessage(responseLogin.message)
                            val editor = SP.edit()
                            editor.putString("id_user", responseLogin.id)
                            editor.apply()
                            startActivity(Intent(applicationContext,
                                com.example.projectmagang.siswa.ActivityUtama::class.java))
                            finish()
                        } else { showMessage("Pengguna Tidak Ditemukan") }
                    } else{ showMessage(responseLogin.message) }
                }
            }
        })
    }

    fun showMessage(message : String){
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        Toast.makeText(this@ActivityLogin, "Back is Clicked", Toast.LENGTH_SHORT).show()
        alertDialog.setTitle("Close Application")
        alertDialog.setMessage("Do you want to close the application ?")
            .setCancelable(false)
            .setPositiveButton("YES", object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, id:Int) {
                    finishAffinity()
                }
            })
            .setNegativeButton("NO", object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, id:Int) {
                    dialog.cancel()
                }
            }).create().show()
    }
}