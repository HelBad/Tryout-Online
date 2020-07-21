package com.example.projectmagang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.modul.DataNilaiSiswa
import kotlinx.android.synthetic.main.cardsiswa_nilai.view.*

class NilaiSiswaAdapter (val context : Context, var dataNilai : ArrayList<DataNilaiSiswa>):
    RecyclerView.Adapter<NilaiSiswaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cardsiswa_nilai, parent, false)
        )
    override fun getItemCount() = dataNilai.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position==0) {
            holder.bing(dataNilai[position])
        }
        else { holder.bing(dataNilai[position]) }
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(dataNilai: DataNilaiSiswa){
            view.mapelNilaiS.text = dataNilai.nama_mapel
            view.guruNilaiS.text = dataNilai.nama_guru
            view.hasilNilaiS.text = dataNilai.nilai.toString()
        }
    }

    fun setData(newDataNilai: List<DataNilaiSiswa>) {
        dataNilai.clear()
        dataNilai.addAll(newDataNilai)
        notifyDataSetChanged()
    }
}