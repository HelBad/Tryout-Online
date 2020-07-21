package com.example.projectmagang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.modul.DataSiswa
import kotlinx.android.synthetic.main.cardguru_siswa.view.*

//class ListSiswaAdapter(siswaList: List<Siswa>, activity: Activity) :
//    RecyclerView.Adapter<ListSiswaAdapter.MyViewHolder?>() {
//    private val siswaList: List<Siswa>
//    private val activity: Activity
//
//    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var namaSiswa: TextView
//        var nisnSiswa: TextView
//        var kelasSiswa: TextView
//        var cardguruSiswa: CardView
//
//        init {
//            namaSiswa = view.findViewById(R.id.namaSiswa)
//            nisnSiswa = view.findViewById(R.id.nisnSiswa)
//            kelasSiswa = view.findViewById(R.id.kelasSiswa)
//            cardguruSiswa = view.findViewById(R.id.cardguruSiswa)
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
//        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.cardguru_siswa, parent, false)
//        return MyViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val siswa: Siswa = siswaList[position]
//        holder.namaSiswa.text = siswa.nama
//        holder.nisnSiswa.text = siswa.nisn
//        holder.kelasSiswa.text = siswa.nama_kelas
//        holder.cardguruSiswa.setOnClickListener {
//            val goDetail = Intent(activity, ActivityDetailSiswa::class.java)
////            goDetail.putExtra("id", siswa.id_user)
////            goDetail.putExtra("nisn", siswa.nisn)
////            goDetail.putExtra("nama", siswa.nama)
////            goDetail.putExtra("email", siswa.email)
////            goDetail.putExtra("jenis_kelamin", siswa.jenis_kelamin)
////            goDetail.putExtra("telp", siswa.telp)
////            goDetail.putExtra("alamat", siswa.alamat)
////            goDetail.putExtra("tanggal_lahir", siswa.tanggal_lahir)
////            goDetail.putExtra("tempat_lahir", siswa.tempat_lahir)
////            goDetail.putExtra("username", siswa.username)
////            goDetail.putExtra("nama_kelas", siswa.nama_kelas)
////            goDetail.putExtra("nama_jurusan", siswa.nama_jurusan)
//            activity.startActivity(goDetail)
//        }
//    }
//
//    init {
//        this.siswaList = siswaList
//        this.activity = activity
//    }
//
//    override fun getItemCount(): Int {
//        return siswaList.size
//    }
//}
class SiswaAdapter (val context : Context, var dataSiswa: ArrayList<DataSiswa>):
RecyclerView.Adapter<SiswaAdapter.ViewHolder>() {
    lateinit var onDetail : OnItemClickCallback

    fun setOnDetailCallback(onItemClickCallback: OnItemClickCallback) {
        this.onDetail = onItemClickCallback
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.cardguru_siswa, parent, false)
    )
    override fun getItemCount() = dataSiswa.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bing(dataSiswa[position])
        holder.view.Siswa.setOnClickListener { onDetail.onItemClicked(dataSiswa[position]) }
    }

    class ViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val view = view
        fun bing(dataSiswa: DataSiswa){
            view.namaSiswa.text = dataSiswa.nama
            view.nisnSiswa.text = dataSiswa.nisn
            view.kelasSiswa.text = dataSiswa.nama_kelas
        }
    }

    fun setData(newDataSiswa: List<DataSiswa>) {
        dataSiswa.clear()
        dataSiswa.addAll(newDataSiswa)
        notifyDataSetChanged()
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: DataSiswa)
    }
}