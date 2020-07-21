package com.example.projectmagang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.modul.DataNilaiGuru
import kotlinx.android.synthetic.main.cardguru_nilai.view.*

class NilaiGuruAdapter (val context : Context, var dataNilai : ArrayList<DataNilaiGuru>):
    RecyclerView.Adapter<NilaiGuruAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cardguru_nilai, parent, false)
        )
    override fun getItemCount() = dataNilai.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position==0) {
            holder.bing(dataNilai[position], "")
        }
        else { holder.bing(dataNilai[position], dataNilai[position-1].nama_kelas) }
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(dataNilai: DataNilaiGuru, kelas : String){
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

    fun setData(newDataNilai: List<DataNilaiGuru>) {
        dataNilai.clear()
        dataNilai.addAll(newDataNilai)
        notifyDataSetChanged()
    }
}