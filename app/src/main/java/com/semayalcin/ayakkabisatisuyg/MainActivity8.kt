package com.semayalcin.ayakkabisatisuyg

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity8 : AppCompatActivity() {

    private lateinit var db : DataBase

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        db = DataBase(applicationContext)
        val cirolar = findViewById<TextView>(R.id.cirolar)
        val geri = findViewById<Button>(R.id.geri5)

        val satici = db.readciro()
        cirolar.text = "SATICI ID" + "\t\t\t" + "SATICI CİRO" + "\n"
        satici.forEach {
            cirolar.text = cirolar.text.toString() + "\n" + it.saticiId + "\t\t\t\t\t" + it.saticiCiro
        }

        geri.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}