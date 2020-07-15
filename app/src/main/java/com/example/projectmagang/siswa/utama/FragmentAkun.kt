package com.example.projectmagang.siswa.utama

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.projectmagang.ActivityLogin
import com.example.projectmagang.R
import com.example.projectmagang.data.ProfilGuru
import com.example.projectmagang.data.ProfilSiswa
import com.example.projectmagang.network.ApiService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentAkun : Fragment() {
    lateinit var SP : SharedPreferences
    lateinit var textNama : TextView
    lateinit var textNISN : TextView
    lateinit var textUsername : TextView
    lateinit var textEmail : TextView
    lateinit var textTTL : TextView
    lateinit var textJenKel : TextView
    lateinit var textAlamat : TextView
    lateinit var textTelp : TextView
    lateinit var gambarAkun : ImageView
    lateinit var btn_logout : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_akun_siswa, container, false)
        SP = activity!!.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        textNama = view.findViewById(R.id.namaAkunS)
        textNISN = view.findViewById(R.id.nisnAkunS)
        textUsername = view.findViewById(R.id.usernameAkunS)
        textEmail = view.findViewById(R.id.emailAkunS)
        textTTL = view.findViewById(R.id.ttlAkunS)
        textJenKel = view.findViewById(R.id.genderAkunS)
        textAlamat = view.findViewById(R.id.alamatAkunS)
        textTelp = view.findViewById(R.id.telpAkunS)
        gambarAkun = view.findViewById(R.id.gambarAkunS)
        btn_logout = view.findViewById(R.id.btn_logoutS)

        btn_logout.setOnClickListener {
            doLogout()
        }
        getContent(SP.getString("iduser","").toString())
        return view
    }

    fun doLogout(){
        val editor = SP.edit()
        editor.putString("iduser","")
        editor.apply()
        startActivity(Intent(activity!!.applicationContext, ActivityLogin::class.java))
        activity!!.finish()
    }
    fun dateFormat (date : String): String {
        val tahun = date.subSequence(0,4).toString()
        val bulan = date.subSequence(5,7).toString()
        val hari = date.subSequence(8,10).toString()
        return when(bulan.toInt()){
            1-> "$hari Januari $tahun"
            2-> "$hari Februari $tahun"
            3-> "$hari Maret $tahun"
            4-> "$hari April $tahun"
            5-> "$hari Mei $tahun"
            6-> "$hari Juni $tahun"
            7-> "$hari Juli $tahun"
            8-> "$hari Agustus $tahun"
            9-> "$hari September $tahun"
            10-> "$hari Oktober $tahun"
            11-> "$hari November $tahun"
            12-> "$hari Desember $tahun"
            else -> ""
        }
    }

    private fun getContent(id : String){
        ApiService.endpoint.profilSiswa(id)
            .enqueue(object : Callback<ProfilSiswa> {
                override fun onFailure(call: Call<ProfilSiswa>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ProfilSiswa>, response: Response<ProfilSiswa>) {
                    if(response.isSuccessful){
                        val dataProfil : ProfilSiswa? = response.body()
                        textNama.text = dataProfil!!.nama
                        textNISN.text = dataProfil.nisn
                        textUsername.text = dataProfil.username
                        textEmail.text = dataProfil.email
                        textTTL.text = dataProfil.tempat_lahir+", "+dateFormat(dataProfil.tanggal_lahir.toString())
                        textJenKel.text = dataProfil.jenkel
                        textAlamat.text = dataProfil.alamat
                        textTelp.text = "+62 "+dataProfil.telp.toString()

                        Picasso.get().load("http://192.168.1.13/tryoutonline/storage/foto/siswa/"+dataProfil.foto).into(gambarAkun)
                    }
                }

            })
    }

}