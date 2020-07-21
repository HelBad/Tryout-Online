package com.example.projectmagang.guru

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.projectmagang.R
import com.example.projectmagang.guru.akun.FragmentAkun
import com.example.projectmagang.guru.home.FragmentHome
import com.example.projectmagang.guru.nilai.FragmentNilai
import com.example.projectmagang.guru.siswa.FragmentSiswa
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_utama_guru.*

class ActivityUtama : AppCompatActivity() {
    lateinit var alertDialog: AlertDialog.Builder

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.homeGuru -> {
                replaceFragment(FragmentHome())
                return@OnNavigationItemSelectedListener true
            }
            R.id.siswaGuru -> {
                replaceFragment(FragmentSiswa())
                return@OnNavigationItemSelectedListener true
            }
            R.id.nilaiGuru -> {
                replaceFragment(FragmentNilai())
                return@OnNavigationItemSelectedListener true
            }
            R.id.akunGuru -> {
                replaceFragment(FragmentAkun())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utama_guru)

        alertDialog = AlertDialog.Builder(this)
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        replaceFragment(FragmentHome())
//        addFragment(FragmentHome())
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer, fragment)
        fragmentTransition.commit()
    }

//    private fun addFragment(fragment: Fragment) {
//        val fragmentTransition = supportFragmentManager.beginTransaction()
//        fragmentTransition.add(R.id.fragmentContainer, fragment)
//        fragmentTransition.commit()
//    }

    override fun onBackPressed() {
        Toast.makeText(this, "Back is Clicked", Toast.LENGTH_SHORT).show()
        alertDialog.setTitle("Close Application")
        alertDialog.setMessage("Do you want to close the application ?")
            .setCancelable(false)
            .setPositiveButton("YES", object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, id:Int) {
                    finishAffinity()
                }
            })
            .setNegativeButton("NO", object: DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface, id:Int) {
                    dialog.cancel()
                }
            }).create().show()
    }
}