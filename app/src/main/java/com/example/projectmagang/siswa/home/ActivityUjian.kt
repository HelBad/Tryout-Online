package com.example.projectmagang.siswa.home

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.adapter.SoalSiswaAdapter
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.model.DataSoalSiswa
import com.example.projectmagang.model.ResponseDataSoalSiswa
import kotlinx.android.synthetic.main.activity_ujian_siswa.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.TimeUnit

class ActivityUjian : AppCompatActivity() {
    lateinit var recyclerSoalS : RecyclerView
    lateinit var soalSiswaAdapter: SoalSiswaAdapter
    lateinit var SP : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ujian_siswa)

        SP = getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        getSoal(SP.getString("id_user","").toString(), intent.getStringExtra("id_mapel"))
        recyclerSoalS = findViewById(R.id.recyclerSoalS)
        soalSiswaAdapter = SoalSiswaAdapter(applicationContext, arrayListOf())

        recyclerSoalS.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = soalSiswaAdapter
        }

        val minute:Long = 1000 * 60
//        val millisInFuture:Long = (minute * 120)
        val millisInFuture:Long = (minute /6)
        val countDownInterval:Long = 1000
        timer(millisInFuture, countDownInterval).start()

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
                tesTimer.text = timeRemaining
            }
            override fun onFinish() {
//                tesTimer.text = "Done"
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
}