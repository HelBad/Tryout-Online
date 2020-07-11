package com.example.projectmagang.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.modul.Record
import com.example.projectmagang.siswa.ActivityProfil

class ListAdapter(siswaList: List<Record>, activity: Activity) :
    RecyclerView.Adapter<ListAdapter.MyViewHolder?>() {
    private val siswaList: List<Record>
    private val activity: Activity

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var namaSiswa: TextView
        var nisnSiswa: TextView
        var kelasSiswa: TextView
        var cardguruSiswa: CardView

        init {
            namaSiswa = view.findViewById(R.id.namaSiswa)
            nisnSiswa = view.findViewById(R.id.nisnSiswa)
            kelasSiswa = view.findViewById(R.id.kelasSiswa)
            cardguruSiswa = view.findViewById(R.id.cardguruSiswa)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.cardguru_siswa, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val siswa: Record = siswaList[position]
        holder.namaSiswa.text = siswa.nama
        holder.nisnSiswa.text = siswa.nip
        holder.kelasSiswa.text = siswa.id_mapel
        holder.cardguruSiswa.setOnClickListener {
            val goDetail = Intent(activity, ActivityProfil::class.java)

            goDetail.putExtra("id_user", siswa.id_user)
            goDetail.putExtra("nip", siswa.nip)
            goDetail.putExtra("nama", siswa.nama)
            goDetail.putExtra("email", siswa.email)
            goDetail.putExtra("jenis_kelamin", siswa.jenis_kelamin)
            goDetail.putExtra("telp", siswa.telp)
            goDetail.putExtra("alamat", siswa.alamat)
            goDetail.putExtra("tanggal_lahir", siswa.tanggal_lahir)
            goDetail.putExtra("tempat_lahir", siswa.tempat_lahir)
            goDetail.putExtra("username", siswa.username)
            goDetail.putExtra("id_mapel", siswa.id_mapel)
            activity.startActivity(goDetail)
        }
    }

    init {
        this.siswaList = siswaList
        this.activity = activity
    }

    override fun getItemCount(): Int {
        return siswaList.size
    }
}