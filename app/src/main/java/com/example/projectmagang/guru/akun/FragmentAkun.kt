package com.example.projectmagang.guru.akun

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.projectmagang.ActivityLogin
import com.example.projectmagang.R
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.modul.ProfilGuru
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentAkun : Fragment() {
    lateinit var SP : SharedPreferences
    lateinit var namaAkun : TextView
    lateinit var nipAkun : TextView
    lateinit var usernameAkun : TextView
    lateinit var emailAkun : TextView
    lateinit var tempatlahirAkun : TextView
    lateinit var tgllahirAkun : TextView
    lateinit var genderAkun : TextView
    lateinit var alamatAkun : TextView
    lateinit var telpAkun : TextView
    lateinit var gambarAkun : ImageView
    lateinit var editAkun : TextView
    var dataFoto: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_akun_guru, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val actionBar = activity!!.findViewById(R.id.toolbarAkun) as Toolbar
        (activity as AppCompatActivity).setSupportActionBar(actionBar)

        SP = activity!!.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        namaAkun = view!!.findViewById(R.id.namaAkun)
        nipAkun = view!!.findViewById(R.id.nipAkun)
        usernameAkun = view!!.findViewById(R.id.usernameAkun)
        emailAkun = view!!.findViewById(R.id.emailAkun)
        tempatlahirAkun = view!!.findViewById(R.id.tempatlahirAkun)
        tgllahirAkun = view!!.findViewById(R.id.tgllahirAkun)
        genderAkun = view!!.findViewById(R.id.genderAkun)
        alamatAkun = view!!.findViewById(R.id.alamatAkun)
        telpAkun = view!!.findViewById(R.id.telpAkun)
        gambarAkun = view!!.findViewById(R.id.gambarAkun)
        editAkun = view!!.findViewById(R.id.editAkun)

        editAkun.setOnClickListener {
            val intent = Intent(activity!!.applicationContext, ActivityProfil::class.java)
            intent.putExtra("nip", nipAkun.text.toString())
            intent.putExtra("nama", namaAkun.text.toString())
            intent.putExtra("username", usernameAkun.text.toString())
            intent.putExtra("email", emailAkun.text.toString())
            intent.putExtra("tempatlahir", tempatlahirAkun.text.toString())
            intent.putExtra("tgllahir", tgllahirAkun.text.toString())
            intent.putExtra("gender", genderAkun.text.toString())
            intent.putExtra("alamat", alamatAkun.text.toString())
            intent.putExtra("telp", telpAkun.text.toString())
            intent.putExtra("foto", dataFoto.toString())
            startActivity(intent)
        }
        getContent(SP.getString("id_user","").toString())
    }

    override fun onPause() {
        super.onPause()
        getContent(SP.getString("id_user", "").toString())
    }

    fun doLogout(){
        val editor = SP.edit()
        editor.putString("id_user","")
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
        UtilsAPI().apiService.profilGuru(id).enqueue(object : Callback<ProfilGuru> {
            override fun onFailure(call: Call<ProfilGuru>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<ProfilGuru>, response: Response<ProfilGuru>) {
                if(response.isSuccessful) {
                    val dataProfil : ProfilGuru? = response.body()
                    namaAkun.text = dataProfil!!.nama
                    nipAkun.text = dataProfil.nip
                    usernameAkun.text = dataProfil.username
                    emailAkun.text = dataProfil.email
                    tempatlahirAkun.text = dataProfil.tempat_lahir
                    tgllahirAkun.text = dateFormat(dataProfil.tanggal_lahir.toString())
                    genderAkun.text = dataProfil.jenis_kelamin
                    alamatAkun.text = dataProfil.alamat
                    telpAkun.text = "+62 "+dataProfil.telp.toString()
                    dataFoto = "http://192.168.43.176/tryoutonline/storage/foto/guru/" + dataProfil.foto
                    Picasso.get().load(dataFoto).into(gambarAkun)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflater: MenuInflater = activity!!.menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuAbout -> {
                doLogout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}