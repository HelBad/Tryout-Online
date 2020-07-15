package com.example.projectmagang.guru.utama

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
import android.widget.Toast
import com.example.projectmagang.ActivityLogin
import com.example.projectmagang.R
import com.example.projectmagang.data.ProfilGuru
import com.example.projectmagang.guru.ActivityProfil
import com.example.projectmagang.network.ApiService
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_akun_guru.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class FragmentAkun : Fragment() {
    lateinit var SP : SharedPreferences
    lateinit var textNama : TextView
    lateinit var textNIP : TextView
    lateinit var textUsername : TextView
    lateinit var textEmail : TextView
    lateinit var textTTL : TextView
    lateinit var textJenKel : TextView
    lateinit var textAlamat : TextView
    lateinit var textTelp : TextView
    lateinit var gambarAkun : ImageView
    lateinit var btn_logout : Button
    lateinit var data : ProfilGuru


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_akun_guru, container, false)
        SP = activity!!.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        textNama = view.findViewById(R.id.namaAkun)
        textNIP = view.findViewById(R.id.nipAkun)
        textUsername = view.findViewById(R.id.usernameAkun)
        textEmail = view.findViewById(R.id.emailAkun)
        textTTL = view.findViewById(R.id.ttlAkun)
        textJenKel = view.findViewById(R.id.genderAkun)
        textAlamat = view.findViewById(R.id.alamatAkun)
        textTelp = view.findViewById(R.id.telpAkun)
        gambarAkun = view.findViewById(R.id.gambarAkun)
        btn_logout = view.findViewById(R.id.btn_logout)


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
        val data : ProfilGuru?
        ApiService.endpoint.profilGuru(id)
            .enqueue(object : Callback<ProfilGuru>{
                override fun onFailure(call: Call<ProfilGuru>, t: Throwable) {
                    t.printStackTrace();
                }

                override fun onResponse(call: Call<ProfilGuru>, response: Response<ProfilGuru>) {
                    if(response.isSuccessful){
                        val dataProfil : ProfilGuru? = response.body()

                        textNama.text = dataProfil!!.nama
                        textNIP.text = dataProfil.nip
                        textUsername.text = dataProfil.username
                        textEmail.text = dataProfil.email
                        textTTL.text = dataProfil.tempat_lahir+", "+dateFormat(dataProfil.tanggal_lahir.toString())
                        textJenKel.text = dataProfil.jenkel
                        textAlamat.text = dataProfil.alamat
                        textTelp.text = "+62 "+dataProfil.telp.toString()

                        Picasso.get().load("http://192.168.1.13/tryoutonline/storage/foto/guru/"+dataProfil.foto).into(gambarAkun)
                    }
                }

            })
    }
}