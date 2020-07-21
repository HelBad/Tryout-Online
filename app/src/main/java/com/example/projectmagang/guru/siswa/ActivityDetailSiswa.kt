package com.example.projectmagang.guru.siswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.projectmagang.R
import com.example.projectmagang.api.BaseApiService
import com.example.projectmagang.api.UtilsAPI

class ActivityDetailSiswa : AppCompatActivity() {
    lateinit var toolbarDetailS: Toolbar
    lateinit var mApiService: BaseApiService
    lateinit var nisnDetailS: TextView
    lateinit var namaDetailS: TextView
    lateinit var usernameDetailS: TextView
    lateinit var emailDetailS: TextView
    lateinit var kelasDetailS: TextView
    lateinit var jurusanDetailS: TextView
    lateinit var tempatlahirDetailS: TextView
    lateinit var tgllahirDetailS: TextView
    lateinit var genderDetailS: TextView
    lateinit var alamatDetailS: TextView
    lateinit var telpDetailS: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailsiswa_guru)

        toolbarDetailS = findViewById(R.id.toolbarDetailS)
        setSupportActionBar(toolbarDetailS)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarDetailS.setNavigationOnClickListener {
            finish()
        }

        mApiService = UtilsAPI().apiService

        nisnDetailS = findViewById(R.id.nisnDetailS)
        namaDetailS = findViewById(R.id.namaDetailS)
        usernameDetailS = findViewById(R.id.usernameDetailS)
        emailDetailS = findViewById(R.id.emailDetailS)
        kelasDetailS = findViewById(R.id.kelasDetailS)
        jurusanDetailS = findViewById(R.id.jurusanDetailS)
        tempatlahirDetailS = findViewById(R.id.tempatlahirDetailS)
        tgllahirDetailS = findViewById(R.id.tgllahirDetailS)
        genderDetailS = findViewById(R.id.genderDetailS)
        alamatDetailS = findViewById(R.id.alamatDetailS)
        telpDetailS = findViewById(R.id.telpDetailS)

        nisnDetailS.text = intent.getStringExtra("nisn")
        namaDetailS.text = intent.getStringExtra("nama")
        usernameDetailS.text = intent.getStringExtra("username")
        emailDetailS.text = intent.getStringExtra("email")
        kelasDetailS.text = intent.getStringExtra("nama_kelas")
        jurusanDetailS.text = intent.getStringExtra("nama_jurusan")
        tempatlahirDetailS.text = intent.getStringExtra("tempat_lahir")
        tgllahirDetailS.text = intent.getStringExtra("tanggal_lahir")
        genderDetailS.text = intent.getStringExtra("jenis_kelamin")
        alamatDetailS.text = intent.getStringExtra("alamat")
        telpDetailS.text = intent.getStringExtra("telp")

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
////                namaDetailS = findViewById(R.id.namaDetailS)
////                namaDetailS.text = intent.getStringExtra("nama")
////                if (myResponse!!.nama == intent.getStringExtra("nama")) {
//                    Toast.makeText(applicationContext, "Inside on Response", Toast.LENGTH_SHORT).show()
//                    runOnUiThread(object:Runnable {
//                        override fun run() {
////                            nisnDetailS = findViewById(R.id.nisnDetailS)
////                            nisnDetailS.text = myResponse!!.nip
////                            namaDetailS = findViewById(R.id.namaDetailS)
////                            namaDetailS.text = intent.getStringExtra("nama")
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