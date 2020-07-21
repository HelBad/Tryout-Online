package com.example.projectmagang.guru.siswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.projectmagang.R
import com.example.projectmagang.api.BaseApiService
import com.example.projectmagang.api.UtilsAPI
import com.squareup.picasso.Picasso

class ActivityDetailSiswa : AppCompatActivity() {
    lateinit var toolbarDetailS: Toolbar
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
    lateinit var fotoDetailS: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailsiswa_guru)

        toolbarDetailS = findViewById(R.id.toolbarDetailS)
        setSupportActionBar(toolbarDetailS)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarDetailS.setNavigationOnClickListener {
            finish()
        }
        getData()
    }

    fun dateFormat (date : String): String {
        val tahun = date.subSequence(0,4).toString()
        val bulan = date.subSequence(5,7).toString()
        val hari = date.subSequence(8,10).toString()
        return when(bulan.toInt()){
            1-> "$hari Januari $tahun"
            2-> "$hari Februari $tahun"
            3-> "$hari Maret $tahun"
            4-> "$hari April $tahun"
            5-> "$hari Mei $tahun"
            6-> "$hari Juni $tahun"
            7-> "$hari Juli $tahun"
            8-> "$hari Agustus $tahun"
            9-> "$hari September $tahun"
            10-> "$hari Oktober $tahun"
            11-> "$hari November $tahun"
            12-> "$hari Desember $tahun"
            else -> ""
        }
    }

    fun getData() {
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
        fotoDetailS = findViewById(R.id.fotoDetailS)

        nisnDetailS.text = intent.getStringExtra("nisn")
        namaDetailS.text = intent.getStringExtra("nama")
        usernameDetailS.text = intent.getStringExtra("username")
        emailDetailS.text = intent.getStringExtra("email")
        kelasDetailS.text = intent.getStringExtra("nama_kelas")
        jurusanDetailS.text = intent.getStringExtra("nama_jurusan")
        tempatlahirDetailS.text = intent.getStringExtra("tempat_lahir")
        tgllahirDetailS.text = dateFormat(intent.getStringExtra("tanggal_lahir"))
        genderDetailS.text = intent.getStringExtra("jenis_kelamin")
        alamatDetailS.text = intent.getStringExtra("alamat")
        telpDetailS.text = "+62 " + intent.getStringExtra("telp")
        Picasso.get().load(intent.getStringExtra("foto")).into(fotoDetailS)
    }
}