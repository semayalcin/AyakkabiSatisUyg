package com.semayalcin.ayakkabisatisuyg

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity5 : AppCompatActivity() {

    private lateinit var db : DataBase
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        db = DataBase(applicationContext)
        val isim = findViewById<TextView>(R.id.isim)
        val no = findViewById<TextView>(R.id.no)
        val stok = findViewById<TextView>(R.id.stok)
        val fiyat = findViewById<Button>(R.id.fiyatButton)
        val geri = findViewById<Button>(R.id.geri3)

        val id = intent.getIntExtra("id",0)
        no.text = intent.getStringExtra("no")

        val stok40 = db.readStok(id, "40")
        val stok41 = db.readStok(id, "41")
        val stok42 = db.readStok(id, "42")
        val stok43 = db.readStok(id, "43")
        val stok44 = db.readStok(id, "44")

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

        geri.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("ayakkabiadi", isim.text.toString())
            intent.putExtra("ayakkabiid", id)
            intent.putExtra("fiyat", fiyat.text.toString().toInt())
            intent.putExtra("stok40",stok40)
            intent.putExtra("stok41",stok41)
            intent.putExtra("stok42",stok42)
            intent.putExtra("stok43",stok43)
            intent.putExtra("stok44",stok44)
            startActivity(intent)
            finish()
        }
    }
}