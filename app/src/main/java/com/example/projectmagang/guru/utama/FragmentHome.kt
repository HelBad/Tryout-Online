package com.example.projectmagang.guru.utama

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectmagang.R
import com.example.projectmagang.guru.ActivityProfil
import com.example.projectmagang.guru.ActivitySoal
import kotlinx.android.synthetic.main.fragment_home_guru.*

class FragmentHome : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home_guru, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addHome.setOnClickListener {
            val intent = Intent(activity, ActivitySoal::class.java)
            startActivity(intent)
        }
    }
}