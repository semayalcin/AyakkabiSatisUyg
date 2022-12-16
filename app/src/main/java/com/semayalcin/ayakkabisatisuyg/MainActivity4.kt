package com.semayalcin.ayakkabisatisuyg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity4 : AppCompatActivity() {
    private lateinit var db: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        db = DataBase(applicationContext)

        val primEkle = findViewById<Button>(R.id.primEkle)
        val saticiKodu = findViewById<EditText>(R.id.saticiKodu)
        val lenght = 5
        saticiKodu.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(lenght))

        primEkle.setOnClickListener {
            if (saticiKodu.text.toString().length < 5) {
                saticiKodu.error = "5 char minimum!"
            } else {
                saticiKodu.error = null
                val fiyat = intent.getIntExtra("ucret",0)
                val result = db.saticiCiro(fiyat, saticiKodu.text.toString().toInt())
                if (result) {
                    Toast.makeText(this, "Ekleme yapıldı.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Ekleme başarısız.", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}
