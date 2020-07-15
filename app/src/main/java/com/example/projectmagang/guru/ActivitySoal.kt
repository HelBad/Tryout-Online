package com.example.projectmagang.guru

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.projectmagang.R
import kotlinx.android.synthetic.main.activity_soal_guru.*
import java.text.SimpleDateFormat
import java.util.*

class ActivitySoal : AppCompatActivity() {
    lateinit var toolbarSoal: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_soal_guru)

        toolbarSoal = findViewById(R.id.toolbarSoal)
        setSupportActionBar(toolbarSoal)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbarSoal.setNavigationOnClickListener {
            finish()
        }

        addSoal.setOnClickListener {
            val intent = Intent(this, ActivityAddSoal::class.java)
            startActivity(intent)
        }
    }

//    lateinit var spinnerBerangkat: Spinner
//    lateinit var textBerangkat: TextView
//
//    val formatHari = SimpleDateFormat("EEEE")
//    var formatTgl = SimpleDateFormat("dd MMM YYYY")
//    val berangkat = Calendar.getInstance()
//    var formatWaktu = SimpleDateFormat("hh:mm aa")
//
//    fun dataSoal() {
//        spinnerBerangkat = findViewById(R.id.spinnerBerangkat)
//        textBerangkat = findViewById(R.id.textBerangkat)
//        val lokasiBerangkat = arrayOf("Malang", "Surabaya")
//        spinnerBerangkat.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lokasiBerangkat)
//        spinnerBerangkat.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                textBerangkat.text = "Pilih Lokasi"
//            }
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                textBerangkat.text = lokasiBerangkat[position]
//            }
//        }
//
//        imgBerangkat.setOnClickListener {
//            val dateBerangkat = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
//                    view, year, month, dayOfMonth -> val selectedDate = Calendar.getInstance()
//                selectedDate.set(Calendar.YEAR, year)
//                selectedDate.set(Calendar.MONTH, month)
//                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//                val date = Date(year, month, dayOfMonth - 1)
//                val dayString = formatHari.format(date)
//
//                klikBerangkat.text = ""
//                tanggalBerangkat.text = "$dayString, " + formatTgl.format(selectedDate.time)
//                val jumlahHari = dayOfMonth.toString().toInt() + month.toString().toInt() * 30 + year.toString().toInt() * 360
//                jumlahHariBerangkat.text = jumlahHari.toString()
//            }, berangkat.get(Calendar.YEAR), berangkat.get(Calendar.MONTH), berangkat.get(Calendar.DAY_OF_MONTH))
//            dateBerangkat.show()
//        }
//
//        imgJamBerangkat.setOnClickListener {
//            val timeBerangkat = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener {
//                    view, hourOfDay, minute -> val selectedTime = Calendar.getInstance()
//                selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
//                selectedTime.set(Calendar.MINUTE, minute)
//
//                klikJamBerangkat.text = ""
//                jamBerangkat.text = formatWaktu.format(selectedTime.time)
//            }, berangkat.get(Calendar.HOUR_OF_DAY), berangkat.get(Calendar.MINUTE), false)
//            timeBerangkat.show()
//        }
//    }
}