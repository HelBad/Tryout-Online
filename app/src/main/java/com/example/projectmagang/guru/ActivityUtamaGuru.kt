package com.example.projectmagang.guru

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.projectmagang.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_utama_guru.*

class ActivityUtamaGuru : AppCompatActivity() {
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.home -> {
                replaceFragment(FragmentHomeGuru())
                return@OnNavigationItemSelectedListener true
            }
            R.id.aktivitas -> {
                replaceFragment(FragmentAktivitasGuru())
                return@OnNavigationItemSelectedListener true
            }
            R.id.akun -> {
                replaceFragment(FragmentAkunGuru())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utama_guru)

        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replaceFragment(FragmentHomeGuru())
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer, fragment)
        fragmentTransition.commit()
    }
}