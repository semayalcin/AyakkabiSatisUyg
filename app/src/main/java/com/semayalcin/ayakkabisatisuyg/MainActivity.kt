package com.semayalcin.ayakkabisatisuyg

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var db : DataBase

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DataBase(applicationContext)

        val kodNo = findViewById<EditText>(R.id.kodNo)
        val sorguButton = findViewById<Button>(R.id.sorguButton)
        val guncelle = findViewById<Button>(R.id.guncelle)
        val yeni = findViewById<Button>(R.id.yeni)
        val saticiEkle = findViewById<Button>(R.id.saticiEkle)

        val lenght = 6
        kodNo.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(lenght))

        sorguButton.setOnClickListener {
            var ad = ""
            var fiyat = 0
            if (kodNo.text.toString().length < 6) {
                kodNo.error = "6 char minimum!"
            } else {
                val users = db.read(kodNo.text.toString().toInt())
                users.forEach {
                    ad = it.ayakkabiadi
                    fiyat = it.fiyat
                }
                val stok40 = db.readStok(kodNo.text.toString().toInt(), "40")
                val stok41 = db.readStok(kodNo.text.toString().toInt(), "41")
                val stok42 = db.readStok(kodNo.text.toString().toInt(), "42")
                val stok43 = db.readStok(kodNo.text.toString().toInt(), "43")
                val stok44 = db.readStok(kodNo.text.toString().toInt(), "44")

                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("ayakkabiadi", ad)
                intent.putExtra("ayakkabiid",kodNo.text.toString().toInt())
                intent.putExtra("fiyat", fiyat)
                intent.putExtra("stok40",stok40)
                intent.putExtra("stok41",stok41)
                intent.putExtra("stok42",stok42)
                intent.putExtra("stok43",stok43)
                intent.putExtra("stok44",stok44)
                startActivity(intent)
                finish()
            }

        }
        yeni.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            //db.deleteAllData()
            startActivity(intent)
            finish()
        }
        guncelle.setOnClickListener {
            val intent = Intent(this, MainActivity6::class.java)
            startActivity(intent)
            finish()
        }
        saticiEkle.setOnClickListener {
            val intent = Intent(this, MainActivity7::class.java)
            startActivity(intent)
            finish()
        }

    }

}