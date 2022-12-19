package com.semayalcin.ayakkabisatisuyg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity6 : AppCompatActivity() {
    private lateinit var db: DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        db = DataBase(applicationContext)

        val kodGir = findViewById<EditText>(R.id.kodGir)
        val noGir = findViewById<EditText>(R.id.noGir)
        val adetGir = findViewById<EditText>(R.id.adetGir)
        val yeniAyakkabi = findViewById<Button>(R.id.yeniAyakkabi)
        val geri = findViewById<Button>(R.id.geri4)

        val lenghtkod = 6
        kodGir.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(lenghtkod))

        val lenghtno = 2
        noGir.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(lenghtno))


        yeniAyakkabi.setOnClickListener {
            if(kodGir.text.toString().length < 6){
                kodGir.error = "6 char minimum!"
            }else if(noGir.text.toString().length < 2){
                noGir.error = "2 char minimum!"
            }else {
                kodGir.error = null
                noGir.error = null
                val result = db.update(
                    kodGir.text.toString().toInt(),
                    noGir.text.toString(),
                    adetGir.text.toString().toInt()
                )
                if (result) {
                    Toast.makeText(this, "Ekleme yapıldı.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Ekleme başarısız.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        geri.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}