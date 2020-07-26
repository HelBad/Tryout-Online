package com.example.projectmagang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.model.DataNilai
import kotlinx.android.synthetic.main.cardguru_nilai.view.*

class ListNilaiGuruAdapter (val context : Context, var dataNilai : ArrayList<DataNilai>):
    RecyclerView.Adapter<ListNilaiGuruAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardguru_nilai, parent, false))

    override fun getItemCount() = dataNilai.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(dataNilai[position])
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        val view = view
        fun bing(dataNilai: DataNilai){
            view.namaNilai.text = dataNilai.nama_siswa
            view.nisnNilai.text = dataNilai.nisn.toString()
            view.kelasNilai.text = dataNilai.nama_kelas
            view.hasilNilai.text = dataNilai.nilai.toString()
        }
    }

    fun setData(newDataNilai: List<DataNilai>){
        dataNilai.clear()
        dataNilai.addAll(newDataNilai)
        notifyDataSetChanged()
    }
}