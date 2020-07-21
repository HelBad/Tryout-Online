package com.example.projectmagang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.modul.DataJadwal
import kotlinx.android.synthetic.main.cardsiswa_jadwal.view.*
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class JadwalAdapter (val context : Context, var dataJadwal: ArrayList<DataJadwal>):
    RecyclerView.Adapter<JadwalAdapter.ViewHolder>() {
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
        fun bing(dataJadwal: DataJadwal){
            view.mapelJadwalS.text = dataJadwal.mapel
            view.kelasJadwalS.text = dataJadwal.kelas
            view.tanggalJadwalS.text = dateFormat(dataJadwal.tanggal.toString())
            view.jamJadwalS.text = timeFormat(dataJadwal.waktu.toString())
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
    }

    fun setData(newDataJadwal: List<DataJadwal>) {
        dataJadwal.clear()
        dataJadwal.addAll(newDataJadwal)
        notifyDataSetChanged()
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: DataJadwal)
    }
}