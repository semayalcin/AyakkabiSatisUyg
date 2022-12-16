package com.semayalcin.ayakkabisatisuyg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity5 : AppCompatActivity() {

    private lateinit var db : DataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        db = DataBase(applicationContext)
        val isim = findViewById<TextView>(R.id.isim)
        val no = findViewById<TextView>(R.id.no)
        val stok = findViewById<TextView>(R.id.stok)
        val fiyat = findViewById<Button>(R.id.fiyatButton)

        val id = intent.getIntExtra("id",0)
        no.text = intent.getStringExtra("no")

        val users = db.read(id)
        users.forEach {
            isim.text = it.ayakkabiadi
        }

        val stokekle = db.readMstok(id, no.text.toString())
        stokekle.forEach {
            stok.text = it.stok.toString()
            fiyat.text = it.fiyat.toString()
        }
        fiyat.setOnClickListener {
            if(stok.text.toString().toInt() == 0){
                Toast.makeText(this, "Merkez stokta ürün yok.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val result = db.mstokAzalt(id, no.text.toString())
                if (result) {
                    Toast.makeText(this, "Ürün satıldı.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Ürün satılamadı.", Toast.LENGTH_SHORT).show()
                }
                val intent = Intent(this, MainActivity4::class.java)
                intent.putExtra("ucret", fiyat.text.toString().toInt())
                startActivity(intent)
                finish()
            }

        }



    }
}