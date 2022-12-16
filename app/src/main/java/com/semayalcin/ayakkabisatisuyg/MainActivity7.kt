package com.semayalcin.ayakkabisatisuyg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity7 : AppCompatActivity() {
    private lateinit var db : DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        db = DataBase(applicationContext)
        val satici = findViewById<EditText>(R.id.satici)
        val ekle = findViewById<Button>(R.id.saticiekle)
        val lenght = 5
        satici.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(lenght))

        ekle.setOnClickListener {
            if (satici.text.toString().length < 5) {
                satici.error = "5 char minimum!"
            }
            else {
                satici.error = null
                val result = db.insertSatici(satici.text.toString().toInt(), 0)

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