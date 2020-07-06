package com.example.projectmagang.guru.utama

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectmagang.R
import com.example.projectmagang.guru.ActivityProfil
import kotlinx.android.synthetic.main.fragment_akun_guru.*

class FragmentAkun : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_akun_guru, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        editAkun.setOnClickListener {
            val intent = Intent(activity, ActivityProfil::class.java)
            startActivity(intent)
        }
    }
}