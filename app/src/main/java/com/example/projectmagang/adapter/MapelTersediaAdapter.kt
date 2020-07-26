package com.example.projectmagang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.model.DataMapelDiajukan
import kotlinx.android.synthetic.main.cardguru_mapel.view.*

class MapelTersediaAdapter (val context : Context, var dataMapelDiajukan : ArrayList<DataMapelDiajukan>):
    RecyclerView.Adapter<MapelTersediaAdapter.ViewHolder>() {
    lateinit var onTambahMapel : OnItemClickCallback

    fun setOnTambahMapelCallback(onItemClickCallback: OnItemClickCallback){
        this.onTambahMapel = onItemClickCallback
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view){
        val view = view
        fun bing(dataMapelDiajukan: DataMapelDiajukan){
            view.namaMapel.text = dataMapelDiajukan.nama_mapel
            view.namaKelas.text = dataMapelDiajukan.nama_kelas
            view.btn_tambah_ajukanMapel.visibility = View.VISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardguru_mapel, parent, false))

    override fun getItemCount() = dataMapelDiajukan.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(dataMapelDiajukan[position])
        holder.view.btn_tambah_ajukanMapel.setOnClickListener {
            onTambahMapel.onItemClicked(dataMapelDiajukan[position], position)
        }
    }

    fun setData(newDataMapelDiajukan: List<DataMapelDiajukan>){
        dataMapelDiajukan.clear()
        dataMapelDiajukan.addAll(newDataMapelDiajukan)
        notifyDataSetChanged()
    }

    fun removeData(position: Int){
        dataMapelDiajukan.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, dataMapelDiajukan.size)
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: DataMapelDiajukan, position: Int)
    }
}