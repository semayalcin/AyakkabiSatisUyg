package com.semayalcin.ayakkabisatisuyg

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity3 : AppCompatActivity() {

    private lateinit var db: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        db = DataBase(applicationContext)

        val kod = findViewById<EditText>(R.id.kod)
        val ad = findViewById<EditText>(R.id.ad)
        val fiyatgir = findViewById<EditText>(R.id.fiyatgir)
        val no = findViewById<EditText>(R.id.no)
        val adetgir = findViewById<EditText>(R.id.adetgir)
        val madetgir = findViewById<EditText>(R.id.madetgir)
        val ekle = findViewById<Button>(R.id.ekle)

        val lenghtkod = 6
        kod.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(lenghtkod))

        val lenghtno = 2
        no.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(lenghtno))

        ekle.setOnClickListener {
            val data = Data(
                ayakkabiid = kod.text.toString().toInt(),
                ayakkabiadi = ad.text.toString(),
                fiyat = fiyatgir.text.toString().toInt(),
                no = no.text.toString(),
                stok = adetgir.text.toString().toInt(),
                mstok = madetgir.text.toString().toInt()
            )

            if(kod.text.toString().length < 6){
                kod.error = "6 char minimum!"
            }else if(no.text.toString().length < 2){
                no.error = "2 char minimum!"
            }else{
                kod.error = null
                no.error = null
                val result = db.insertData(data)
                if(result > -1){
                    Toast.makeText(this, "Ekleme yapıldı.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this, "Ekleme başarısız.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}