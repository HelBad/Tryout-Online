package com.example.projectmagang.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.model.DataJadwalSiswa
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.collections.ArrayList

class JadwalSiswaAdapter (val context : Context, var dataJadwal: ArrayList<DataJadwalSiswa>):
    RecyclerView.Adapter<JadwalSiswaAdapter.ViewHolder>() {
    lateinit var onDetail : OnItemClickCallback

    fun setOnDetailCallback(onItemClickCallback: OnItemClickCallback) {
        this.onDetail = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cardsiswa_jadwal, parent, false)
        )
    override fun getItemCount() = dataJadwal.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(dataJadwal[position])
        holder.view.setOnClickListener { onDetail.onItemClicked(dataJadwal[position]) }
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val view = view
        @SuppressLint("NewApi")
        fun bing(dataJadwal: DataJadwalSiswa) {
            val date = getCurrentDateTime()
            val localDate = LocalDate.parse(date.toString("yyyy-MM-dd"), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val dbDate = LocalDate.parse(dataJadwal.tanggal, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            val diffDate = ChronoUnit.DAYS.between(localDate, dbDate)
            val localTime = LocalTime.parse(date.toString("HH:mm:ss"), DateTimeFormatter.ofPattern("HH:mm:ss")).toSecondOfDay()
            val dbTime = LocalTime.parse(dataJadwal.waktu, DateTimeFormatter.ofPattern("HH:mm:ss")).toSecondOfDay()
            val diffTime = ((dbTime + 7200) - localTime)

//            if (diffDate >= 0 && diffTime > 0) {
//                view.mapelJadwalS.text = dataJadwal.mapel
//                view.kelasJadwalS.text = dataJadwal.kelas
//                view.tanggalJadwalS.text = dateFormat(dataJadwal.tanggal.toString())
//                view.jamJadwalS.text = timeFormat(dataJadwal.waktu.toString())
//            } else if (localTime == dbTime && localDate.toString() == dbTime.toString()){
//                view.mapelJadwalS.text = dataJadwal.mapel
//                view.kelasJadwalS.text = dataJadwal.kelas
//                view.tanggalJadwalS.text = dateFormat(dataJadwal.tanggal.toString())
//                view.jamJadwalS.text = timeFormat(dataJadwal.waktu.toString())
//                view.cardJadwalS.isClickable = false
//                view.cardJadwalS.isEnabled = false
//            } else {
//                view.cardJadwalS.visibility = View.GONE
//            }
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
        fun timeFormat(time: String): String {
            val df: DateFormat = SimpleDateFormat("HH:mm:ss")
            val outputformat: DateFormat = SimpleDateFormat("hh:mm aa")
            try {
                val date: Date = df.parse(time)
                val output: String = outputformat.format(date)
                return output
            } catch (pe: ParseException) {
                pe.printStackTrace()
                return ""
            }
        }
        fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
            val formatter = SimpleDateFormat(format, locale)
            return formatter.format(this)
        }

        fun getCurrentDateTime(): Date {
            return Calendar.getInstance().time
        }
    }

    fun setData(newDataJadwal: List<DataJadwalSiswa>) {
        dataJadwal.clear()
        dataJadwal.addAll(newDataJadwal)
        notifyDataSetChanged()
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: DataJadwalSiswa)
    }
}