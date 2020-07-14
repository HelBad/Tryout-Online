package com.example.projectmagang.guru.utama

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.projectmagang.R
import com.example.projectmagang.guru.ActivityProfil
import kotlinx.android.synthetic.main.fragment_akun_guru.*

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
        getContent()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        editAkun.setOnClickListener {
            val intent = Intent(activity, ActivityProfil::class.java)
            startActivity(intent)
        }
    }

    fun getContent(){
        textNama.text = SP.getString("nama","")
        textNIP.text = SP.getInt("nip", 0).toString()
        textUsername.text = SP.getInt("username", 0).toString()
        textEmail.text = SP.getString("email", "")
        textTTL.text = SP.getString("tempatLahir", "")+", "+SP.getString("tanggalLahir","")
        textJenKel.text = SP.getString("jenkel", "")
        textAlamat.text = SP.getString("alamat","")
        textTelp.text = SP.getInt("telp", 0).toString()
    }
}