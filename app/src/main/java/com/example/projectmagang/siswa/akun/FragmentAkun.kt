package com.example.projectmagang.siswa.akun

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.projectmagang.ActivityLogin
import com.example.projectmagang.R
import com.example.projectmagang.api.UtilsAPI
import com.example.projectmagang.modul.ProfilSiswa
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentAkun : Fragment() {
    lateinit var SP : SharedPreferences
    lateinit var namaAkunS : TextView
    lateinit var nisnAkunS : TextView
    lateinit var usernameAkunS : TextView
    lateinit var emailAkunS : TextView
    lateinit var tempatlahirAkunS : TextView
    lateinit var tgllahirAkunS : TextView
    lateinit var genderAkunS : TextView
    lateinit var alamatAkunS : TextView
    lateinit var telpAkunS : TextView
//    lateinit var kelasAkunS : TextView
//    lateinit var jurusanAkunS : TextView
    lateinit var gambarAkunS : ImageView
    lateinit var editAkunS : TextView
    var dataFoto: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_akun_siswa, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val actionBar = activity!!.findViewById(R.id.toolbarAkunS) as Toolbar
        (activity as AppCompatActivity).setSupportActionBar(actionBar)

        SP = activity!!.getSharedPreferences("TryoutOnline", Context.MODE_PRIVATE)
        namaAkunS = view!!.findViewById(R.id.namaAkunS)
        nisnAkunS = view!!.findViewById(R.id.nisnAkunS)
        usernameAkunS = view!!.findViewById(R.id.usernameAkunS)
        emailAkunS = view!!.findViewById(R.id.emailAkunS)
        tempatlahirAkunS = view!!.findViewById(R.id.tempatlahirAkunS)
        tgllahirAkunS = view!!.findViewById(R.id.tgllahirAkunS)
        genderAkunS = view!!.findViewById(R.id.genderAkunS)
        alamatAkunS = view!!.findViewById(R.id.alamatAkunS)
        telpAkunS = view!!.findViewById(R.id.telpAkunS)
//        kelasAkunS = view!!.findViewById(R.id.kelasAkunS)
//        jurusanAkunS = view!!.findViewById(R.id.jurusanAkunS)
        gambarAkunS = view!!.findViewById(R.id.gambarAkunS)
        editAkunS = view!!.findViewById(R.id.editAkunS)

        editAkunS.setOnClickListener {
            val intent = Intent(activity!!.applicationContext, ActivityProfil::class.java)
            intent.putExtra("nisn", nisnAkunS.text.toString())
            intent.putExtra("nama", namaAkunS.text.toString())
            intent.putExtra("username", usernameAkunS.text.toString())
            intent.putExtra("email", emailAkunS.text.toString())
            intent.putExtra("tempatlahir", tempatlahirAkunS.text.toString())
            intent.putExtra("tgllahir", tgllahirAkunS.text.toString())
            intent.putExtra("gender", genderAkunS.text.toString())
            intent.putExtra("alamat", alamatAkunS.text.toString())
            intent.putExtra("telp", telpAkunS.text.toString())
//            intent.putExtra("kelas", kelasAkunS.text.toString())
//            intent.putExtra("jurusan", jurusanAkunS.text.toString())
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
        UtilsAPI().apiService.profilSiswa(id).enqueue(object : Callback<ProfilSiswa> {
            override fun onFailure(call: Call<ProfilSiswa>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(call: Call<ProfilSiswa>, response: Response<ProfilSiswa>) {
                if(response.isSuccessful) {
                    val dataProfil : ProfilSiswa? = response.body()
                    namaAkunS.text = dataProfil!!.nama
                    nisnAkunS.text = dataProfil.nisn
                    usernameAkunS.text = dataProfil.username
                    emailAkunS.text = dataProfil.email
                    tempatlahirAkunS.text = dataProfil.tempat_lahir
                    tgllahirAkunS.text = dateFormat(dataProfil.tanggal_lahir.toString())
                    genderAkunS.text = dataProfil.jenis_kelamin
                    alamatAkunS.text = dataProfil.alamat
                    telpAkunS.text = "+62 "+dataProfil.telp.toString()
//                    kelasAkunS.text = dataProfil.nama_kelas
//                    jurusanAkunS.text = dataProfil.nama_jurusan
                    dataFoto = "http://192.168.1.16/tryoutonline/storage/foto/siswa/" + dataProfil.foto
                    Picasso.get().load(dataFoto).into(gambarAkunS)
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