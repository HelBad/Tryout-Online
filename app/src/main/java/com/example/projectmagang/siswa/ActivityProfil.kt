package com.example.projectmagang.siswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.projectmagang.R
import com.example.projectmagang.api.BaseApiService

class ActivityProfil : AppCompatActivity() {
    lateinit var toolbarProfilS: Toolbar
    lateinit var mApiService: BaseApiService
    lateinit var nisnProfilS: TextView
    lateinit var namaProfilS: TextView
    lateinit var usernameProfilS: TextView
    lateinit var emailProfilS: TextView
    lateinit var kelasProfilS: TextView
    lateinit var jurusanProfilS: TextView
    lateinit var tempatlahirProfilS: TextView
    lateinit var tgllahirProfilS: TextView
    lateinit var genderProfilS: TextView
    lateinit var alamatProfilS: TextView
    lateinit var telpProfilS: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_siswa)

        toolbarProfilS = findViewById(R.id.toolbarProfilS)
        setSupportActionBar(toolbarProfilS)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarProfilS.setNavigationOnClickListener {
            finish()
        }

//        mApiService = UtilsAPI().apiService
//
//        nisnProfilS = findViewById(R.id.nisnProfilS)
//        namaProfilS = findViewById(R.id.namaProfilS)
//        usernameProfilS = findViewById(R.id.usernameProfilS)
//        emailProfilS = findViewById(R.id.emailProfilS)
//        kelasProfilS = findViewById(R.id.kelasProfilS)
//        jurusanProfilS = findViewById(R.id.jurusanProfilS)
//        tempatlahirProfilS = findViewById(R.id.tempatlahirProfilS)
//        tgllahirProfilS = findViewById(R.id.tgllahirProfilS)
//        genderProfilS = findViewById(R.id.genderProfilS)
//        alamatProfilS = findViewById(R.id.alamatProfilS)
//        telpProfilS = findViewById(R.id.telpProfilS)
//
//        nisnProfilS.text = intent.getStringExtra("nip")
//        namaProfilS.text = intent.getStringExtra("nama")
//        usernameProfilS.text = intent.getStringExtra("username")
//        emailProfilS.text = intent.getStringExtra("email")
//        kelasProfilS.text = intent.getStringExtra("id_mapel")
//        jurusanProfilS.text = intent.getStringExtra("id_user")
//        tempatlahirProfilS.text = intent.getStringExtra("tempat_lahir")
//        tgllahirProfilS.text = intent.getStringExtra("tanggal_lahir")
//        genderProfilS.text = intent.getStringExtra("jenis_kelamin")
//        alamatProfilS.text = intent.getStringExtra("alamat")
//        telpProfilS.text = intent.getStringExtra("telp")

//        getDataForId()
    }

//    private val mSiswaDataList = ArrayList<ReadDataResponse>()
//    private val siswaList = ArrayList<Record>()
//    lateinit var mListAdapter: ListAdapter
//
//    fun getDataForId() {
//        mApiService = UtilsAPI().apiService
//        mApiService.getReponse("").enqueue(object: Callback<Record> {
//            override fun onResponse(call: Call<Record>, response: Response<Record>) {
//                val myResponse = response.body()
////                namaProfilS = findViewById(R.id.namaProfilS)
////                namaProfilS.text = intent.getStringExtra("nama")
////                if (myResponse!!.nama == intent.getStringExtra("nama")) {
//                    Toast.makeText(applicationContext, "Inside on Response", Toast.LENGTH_SHORT).show()
//                    runOnUiThread(object:Runnable {
//                        override fun run() {
////                            nisnProfilS = findViewById(R.id.nisnProfilS)
////                            nisnProfilS.text = myResponse!!.nip
////                            namaProfilS = findViewById(R.id.namaProfilS)
////                            namaProfilS.text = intent.getStringExtra("nama")
//
//                        }
//                    })
////                }
//            }
//            override fun onFailure(call: Call<Record>, t: Throwable) {
//                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}