package com.example.projectmagang.guru.utama.nilai

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.modul.DataNilai
import kotlinx.android.synthetic.main.cardguru_nilai.view.*

class NilaiGuruAdapter (val context : Context, var dataNilai : ArrayList<DataNilai>):
    RecyclerView.Adapter<NilaiGuruAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardguru_nilai, parent, false))
    override fun getItemCount() = dataNilai.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position==0) {
            holder.bing(dataNilai[position], "")
        }
        else { holder.bing(dataNilai[position], dataNilai[position-1].nama_kelas) }
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(dataNilai: DataNilai, kelas : String){
            view.namaNilai.text = dataNilai.nama
            view.nisnNilai.text = dataNilai.nisn.toString()
            view.kelasNilai.text = dataNilai.nama_kelas
            view.hasilNilai.text = dataNilai.nilai.toString()
            if(dataNilai.nama_kelas != kelas) {
                view.headKelas.visibility = View.VISIBLE
                view.headKelas.text = dataNilai.nama_kelas
            }
        }
    }

    fun setData(newDataNilai: List<DataNilai>) {
        dataNilai.clear()
        dataNilai.addAll(newDataNilai)
        notifyDataSetChanged()
    }
}