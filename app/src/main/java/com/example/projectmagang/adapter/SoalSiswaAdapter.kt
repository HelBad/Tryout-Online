package com.example.projectmagang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.model.DataSoalSiswa
import kotlinx.android.synthetic.main.cardsiswa_soal.view.*

class SoalSiswaAdapter (val context : Context, var dataSoal : ArrayList<DataSoalSiswa>):
    RecyclerView.Adapter<SoalSiswaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cardsiswa_soal, parent, false)
        )
    override fun getItemCount() = dataSoal.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(position==0) {
            holder.bing(dataSoal[position])
        }
        else { holder.bing(dataSoal[position]) }
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(dataSoal: DataSoalSiswa) {
            view.pilihanA.text = dataSoal.jawab_a
            view.pilihanB.text = dataSoal.jawab_b
            view.pilihanC.text = dataSoal.jawab_c
            view.pilihanD.text = dataSoal.jawab_d
            view.pilihanE.text = dataSoal.jawab_e
            view.soalSiswa.text = dataSoal.soal
        }
    }

    fun setData(newDataSoal: List<DataSoalSiswa>) {
        dataSoal.clear()
        dataSoal.addAll(newDataSoal)
        notifyDataSetChanged()
    }
}