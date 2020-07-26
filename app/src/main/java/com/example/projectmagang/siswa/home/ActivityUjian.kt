package com.example.projectmagang.siswa.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.adapter.SoalSiswaAdapter
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.model.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profil_guru.*
import kotlinx.android.synthetic.main.activity_ujian_siswa.*
import kotlinx.android.synthetic.main.cardsiswa_soal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit

class ActivityUjian : AppCompatActivity() {
    lateinit var recyclerSoalS : RecyclerView
    lateinit var soalSiswaAdapter: SoalSiswaAdapter
    lateinit var SP : SharedPreferences
    lateinit var alertDialog: AlertDialog.Builder

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ujian_siswa)

        alertDialog = AlertDialog.Builder(this)
        SP = getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        getSoal(SP.getString("id_user","").toString(), intent.getStringExtra("id_mapel"))
        recyclerSoalS = findViewById(R.id.recyclerSoalS)
        soalSiswaAdapter = SoalSiswaAdapter(applicationContext, arrayListOf())

        recyclerSoalS.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = soalSiswaAdapter
        }
        finishSoalS.setOnClickListener {
            onBackPressed()
        }

        val date = getCurrentDateTime()
        val localTime = LocalTime.parse(date.toString("HH:mm:ss"), DateTimeFormatter.ofPattern("HH:mm:ss")).toSecondOfDay()
        val dbTime = LocalTime.parse(intent.getStringExtra("waktu"), DateTimeFormatter.ofPattern("HH:mm:ss")).toSecondOfDay()

        val differentTime = ((dbTime + 7200) - localTime) / 60
        val minute:Long = 1000 * 60
        val millisInFuture:Long = (minute * differentTime)
        val countDownInterval:Long = 1000
        timer(millisInFuture, countDownInterval).start()

//        SP = applicationContext.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
//        getJawaban(SP.getString("id_user","")!!.toString(), namaProfil.text.toString(),
//            genderProfil.text.toString(), tempatlahirProfil.text.toString())
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun getSoal(id : String, id_mapel : String){
        UtilsAPI().apiService.soalSiswa(id, id_mapel).enqueue(object : Callback<ResponseDataSoalSiswa> {
            override fun onFailure(call: Call<ResponseDataSoalSiswa>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<ResponseDataSoalSiswa>, response: Response<ResponseDataSoalSiswa>) {
                if(response.isSuccessful) {
                    val data = response.body()
                    val dataSoalSiswa : List<DataSoalSiswa> = data!!.soal
                    soalSiswaAdapter.setData(dataSoalSiswa)
                }
            }
        })
    }

    private fun timer(millisInFuture:Long,countDownInterval:Long):CountDownTimer{
        return object: CountDownTimer(millisInFuture,countDownInterval){
            override fun onTick(millisUntilFinished: Long){
                val timeRemaining = timeString(millisUntilFinished)
                timerCount.text = timeRemaining
            }
            override fun onFinish() {
                alertDialog.setTitle("Ujian Berakhir")
                alertDialog.setMessage("Ujian Selesai, tekan OK untuk kembali ke Home")
                    .setCancelable(false)
                    .setPositiveButton("OK", object: DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface, id:Int) {
                            finish()
                        }
                    }).create().show()
            }
        }
    }

    private fun timeString(millisUntilFinished:Long):String{
        var millisUntilFinished:Long = millisUntilFinished
        val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
        millisUntilFinished -= TimeUnit.HOURS.toMillis(hours)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
        millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)

        return String.format(Locale.getDefault(), "%02d : %02d : %02d", hours, minutes,seconds)
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Tombol Kembali ditekan", Toast.LENGTH_SHORT).show()
        alertDialog.setTitle("Akhiri Ujian")
        alertDialog.setMessage("Apa kamu yakin untuk mengakhiri ujian ?")
            .setCancelable(false)
            .setPositiveButton("YA", object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, id:Int) {
                    finish()
                }
            })
            .setNegativeButton("TIDAK", object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, id:Int) {
                    dialog.cancel()
                }
            }).create().show()
    }

//    fun getJawaban(id: String, id_siswa: String, id_mapel: String, id_soal: String, jawaban: String) {
//        UtilsAPI().apiService.getJawaban(id, id_siswa, id_mapel, id_soal, jawaban).enqueue(object : Callback<ResponseDataJawabanSiswa> {
//            override fun onFailure(call: Call<ResponseDataJawabanSiswa>, t: Throwable) {
//                t.printStackTrace()
//            }
//            override fun onResponse(call: Call<ResponseDataJawabanSiswa>, response: Response<ResponseDataJawabanSiswa>) {
//                if(response.isSuccessful) {
//                    val data = response.body()
//                    Toast.makeText(applicationContext, "Data sedang diproses", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
//    }


}