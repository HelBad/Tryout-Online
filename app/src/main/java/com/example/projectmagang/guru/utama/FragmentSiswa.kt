package com.example.projectmagang.guru.utama

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.adapter.ListSiswaAdapter
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.guru.ActivityDetailSiswa
import com.example.projectmagang.guru.utama.nilai.ActivityNilaiGuru
import com.example.projectmagang.modul.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentSiswa : Fragment() {
//    private val mSiswaDataList = ArrayList<DataSiswaResponse>()
//    private val siswaList = ArrayList<Siswa>()
    lateinit var recyclerSiswa: RecyclerView
    lateinit var listSiswaAdapter: ListSiswaAdapter
    lateinit var SP : SharedPreferences
//    lateinit var mApiService: BaseApiService
//    lateinit var mListAdapter: ListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_siswa_guru, container, false)

        SP = activity!!.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        getSiswa(SP.getString("id_user","").toString())
        recyclerSiswa = rootView.findViewById(R.id.recyclerSiswa)
        listSiswaAdapter = ListSiswaAdapter (activity!!.applicationContext, arrayListOf())

        recyclerSiswa.apply {
            layoutManager = LinearLayoutManager(activity!!.applicationContext)
            listSiswaAdapter.setOnDetailCallback(object : ListSiswaAdapter.OnItemClickCallback {
                override fun onItemClicked(data: DataSiswa) {
                    val intent = Intent(activity!!.applicationContext, ActivityDetailSiswa::class.java)
//                    intent.putExtra("nama_guru", data.nama_guru)
                    startActivity(intent)
                }
            })
            adapter = listSiswaAdapter
        }
//        return view
//
//        mApiService = UtilsAPI().apiService
//        recyclerSiswa = rootView.findViewById(R.id.recyclerSiswa) as RecyclerView
//        val mLayoutManager = LinearLayoutManager(activity!!.applicationContext)
//        mListAdapter = ListAdapter(siswaList, activity!!)
//        recyclerSiswa.layoutManager = mLayoutManager
//        recyclerSiswa.itemAnimator = DefaultItemAnimator()
//        recyclerSiswa.adapter = mListAdapter

        return rootView
    }

    fun getSiswa(id : String){
        UtilsAPI().apiService.listSiswa(id).enqueue(object :Callback<ResponseDataSiswa> {
            override fun onFailure(call: Call<ResponseDataSiswa>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<ResponseDataSiswa>, response: Response<ResponseDataSiswa>) {
                if(response.isSuccessful) {
                    showMessage("Data berhasil di load")
                    val data = response.body()
                    val dataSiswa : List<DataSiswa> = data!!.siswa
                    listSiswaAdapter.setData(dataSiswa)
                }
            }
        })
    }

    fun showMessage(msg : String){
        Toast.makeText(activity!!.applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

//    private fun dataAttachmentCategory() {
//        recyclerSiswa.visibility = View.GONE
//        mSiswaDataList.clear()
//        siswaList.clear()
//        mApiService.readData("", "", "").enqueue(object: Callback<DataSiswaResponse> {
//            override fun onResponse(call: Call<DataSiswaResponse>, response: Response<DataSiswaResponse>) {
//                if (response.isSuccessful) {
//                    try {
//                        val total = response.body()!!.recordsiswa!!.size
//                        for (a in 0 until total) {
//                            val modelSeatGroup = ProfilSiswa(
//                                response.body()!!.recordsiswa!!.get(a).id,
//                                response.body()!!.recordsiswa!!.get(a).nisn,
//                                response.body()!!.recordsiswa!!.get(a).nama,
//                                response.body()!!.recordsiswa!!.get(a).email,
//                                response.body()!!.recordsiswa!!.get(a).jenis_kelamin,
//                                response.body()!!.recordsiswa!!.get(a).telp,
//                                response.body()!!.recordsiswa!!.get(a).alamat,
//                                response.body()!!.recordsiswa!!.get(a).tanggal_lahir,
//                                response.body()!!.recordsiswa!!.get(a).tempat_lahir,
//                                response.body()!!.recordsiswa!!.get(a).username,
//                                response.body()!!.recordsiswa!!.get(a).nama_kelas,
//                                response.body()!!.recordsiswa!!.get(a).nama_jurusan,
//                                response.body()!!.recordsiswa!!.get(a).foto)
//                            siswaList.add(modelSeatGroup)
//                        }
//                        val item = DataSiswaResponse(siswaList)
//                        mSiswaDataList.add(item)
//                        mListAdapter = ListAdapter(siswaList, activity!!)
//                        recyclerSiswa.adapter = mListAdapter
//                        if (siswaList.isEmpty()) {
//                            recyclerSiswa.visibility = View.GONE
//                            Toast.makeText(activity, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
//                        }
//                        else { recyclerSiswa.visibility = View.VISIBLE }
//                    }
//                    catch (e:NullPointerException) { e.printStackTrace() }
//                }
//                else { Toast.makeText(activity, "Please try again, server is down", Toast.LENGTH_SHORT).show() }
//            }
//            override fun onFailure(call: Call<DataSiswaResponse>, t:Throwable) {
//                Toast.makeText(activity, "Please try again, server is down onfail", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }

//    override fun onResume() {
//        super.onResume()
//        dataAttachmentCategory()
//    }
}