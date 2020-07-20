package com.example.projectmagang.guru.utama.nilai

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.modul.DataMapel
import kotlinx.android.synthetic.main.cardnilai_tryout.view.*

class NilaiMapelAdapter (val context : Context, var dataMapel: ArrayList<DataMapel>):
    RecyclerView.Adapter<NilaiMapelAdapter.ViewHolder>() {
    lateinit var onDetail : OnItemClickCallback

    fun setOnDetailCallback(onItemClickCallback: OnItemClickCallback) {
        this.onDetail = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cardnilai_tryout, parent, false)
        )
    override fun getItemCount() = dataMapel.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(dataMapel[position])
        holder.view.Mapel.setOnClickListener { onDetail.onItemClicked(dataMapel[position]) }
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(dataMapel: DataMapel){
            view.namaMapel.text = dataMapel.nama_mapel
            view.kelasMapel.text = dataMapel.nama_kelas
        }
    }

    fun setData(newDataMapel: List<DataMapel>) {
        dataMapel.clear()
        dataMapel.addAll(newDataMapel)
        notifyDataSetChanged()
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: DataMapel)
    }
}