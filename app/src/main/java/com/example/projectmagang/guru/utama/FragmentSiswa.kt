package com.example.projectmagang.guru.utama

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectmagang.R
import com.example.projectmagang.adapter.ListAdapter
import com.example.projectmagang.api.BaseApiService
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.modul.ReadDataResponse
import com.example.projectmagang.modul.Record
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentSiswa : Fragment() {
    private val mSiswaDataList = ArrayList<ReadDataResponse>()
    private val siswaList = ArrayList<Record>()
    lateinit var recyclerSiswa: RecyclerView
    lateinit var mApiService: BaseApiService
    lateinit var mListAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_siswa_guru, container, false)

        mApiService = UtilsAPI().apiService
        recyclerSiswa = rootView.findViewById(R.id.recyclerSiswa) as RecyclerView
        val mLayoutManager = LinearLayoutManager(activity!!.applicationContext)
        mListAdapter = ListAdapter(siswaList, activity!!)
        recyclerSiswa.layoutManager = mLayoutManager
        recyclerSiswa.itemAnimator = DefaultItemAnimator()
        recyclerSiswa.adapter = mListAdapter

        return rootView
    }

    private fun dataAttachmentCategory() {
        recyclerSiswa.visibility = View.GONE
        mSiswaDataList.clear()
        siswaList.clear()
        mApiService.readData("", "", "").enqueue(object: Callback<ReadDataResponse> {
            override fun onResponse(call: Call<ReadDataResponse>, response: Response<ReadDataResponse>) {
                if (response.isSuccessful) {
                    try {

                        val total = response.body()!!.records!!.size
                        for (a in 0 until total) {
                            val modelSeatGroup = Record(
                                response.body()!!.records!!.get(a).id_user,
                                response.body()!!.records!!.get(a).nip,
                                response.body()!!.records!!.get(a).nama,
                                response.body()!!.records!!.get(a).email,
                                response.body()!!.records!!.get(a).jenis_kelamin,
                                response.body()!!.records!!.get(a).telp,
                                response.body()!!.records!!.get(a).alamat,
                                response.body()!!.records!!.get(a).tanggal_lahir,
                                response.body()!!.records!!.get(a).tempat_lahir,
                                response.body()!!.records!!.get(a).username,
                                response.body()!!.records!!.get(a).id_mapel)
                            siswaList.add(modelSeatGroup)
                        }
                        val item = ReadDataResponse(siswaList)
                        mSiswaDataList.add(item)
                        mListAdapter = ListAdapter(siswaList, activity!!)
                        recyclerSiswa.adapter = mListAdapter
                        if (siswaList.isEmpty()) {
                            recyclerSiswa.visibility = View.GONE
                            Toast.makeText(activity, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
                        }
                        else { recyclerSiswa.visibility = View.VISIBLE }
                    }
                    catch (e:NullPointerException) { e.printStackTrace() }
                }
                else { Toast.makeText(activity, "Please try again, server is down", Toast.LENGTH_SHORT).show() }
            }
            override fun onFailure(call: Call<ReadDataResponse>, t:Throwable) {
                Toast.makeText(activity, "Please try again, server is down onfail", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        dataAttachmentCategory()
    }
}