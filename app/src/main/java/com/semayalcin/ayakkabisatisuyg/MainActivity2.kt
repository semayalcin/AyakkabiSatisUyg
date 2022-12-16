package com.semayalcin.ayakkabisatisuyg

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {
    private lateinit var db: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        db = DataBase(applicationContext)

        val ayakkabiadi = findViewById<TextView>(R.id.ayakkabi_adi)
        val fiyat = findViewById<Button>(R.id.fiyat)
        val merkezdepo = findViewById<Button>(R.id.merkez_depo)
        val no40 = findViewById<Button>(R.id.no40)
        val no41 = findViewById<Button>(R.id.no41)
        val no42 = findViewById<Button>(R.id.no42)
        val no43 = findViewById<Button>(R.id.no43)
        val no44 = findViewById<Button>(R.id.no44)

        val id = intent.getIntExtra("ayakkabiid",0)
        ayakkabiadi.text = intent.getStringExtra("ayakkabiadi")
        fiyat.text = intent.getIntExtra("fiyat",0).toString()
        val stok40 = intent.getIntExtra("stok40", 0)
        val stok41 = intent.getIntExtra("stok41", 0)
        val stok42 = intent.getIntExtra("stok42", 0)
        val stok43 = intent.getIntExtra("stok43", 0)
        val stok44 = intent.getIntExtra("stok44", 0)

        if(stok40 == 0){
            no40.setBackgroundColor(Color.parseColor("#FFC12F25"))
            no40.setOnClickListener {
                merkezdepo.setOnClickListener{
                    val intent = Intent(this, MainActivity5::class.java)
                    intent.putExtra("id", id)
                    intent.putExtra("no", "40")
                    startActivity(intent)
                    finish()
                }
            }
        }else if(stok40 > 0){
            no40.setOnClickListener {
                fiyat.setOnClickListener{
                    val result = db.stokAzalt(id, "40")
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

        if(stok41 == 0){
            no41.setBackgroundColor(Color.parseColor("#FFC12F25"))
            no41.setOnClickListener {
                merkezdepo.setOnClickListener{
                    val intent = Intent(this, MainActivity5::class.java)
                    intent.putExtra("id", id)
                    intent.putExtra("no", "41")
                    startActivity(intent)
                    finish()
                }
            }
        }else if(stok41 > 0){
            no41.setOnClickListener {
                fiyat.setOnClickListener{
                    val result = db.stokAzalt(id, "41")
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

        if(stok42 == 0){
            no42.setBackgroundColor(Color.parseColor("#FFC12F25"))
            no42.setOnClickListener {
                merkezdepo.setOnClickListener{
                    val intent = Intent(this, MainActivity5::class.java)
                    intent.putExtra("id", id)
                    intent.putExtra("no", "42")
                    startActivity(intent)
                    finish()
                }
            }
        }else if(stok42 > 0){
            no42.setOnClickListener {
                fiyat.setOnClickListener{
                    val result = db.stokAzalt(id, "42")
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

        if(stok43 == 0){
            no43.setBackgroundColor(Color.parseColor("#FFC12F25"))
            no43.setOnClickListener {
                merkezdepo.setOnClickListener{
                    val intent = Intent(this, MainActivity5::class.java)
                    intent.putExtra("id", id)
                    intent.putExtra("no", "43")
                    startActivity(intent)
                    finish()
                }
            }
        }else if(stok43 > 0){
            no43.setOnClickListener {
                fiyat.setOnClickListener{
                    val result = db.stokAzalt(id, "43")
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

        if(stok44 == 0){
            no44.setBackgroundColor(Color.parseColor("#FFC12F25"))
            no44.setOnClickListener {
                merkezdepo.setOnClickListener{
                    val intent = Intent(this, MainActivity5::class.java)
                    intent.putExtra("id", id)
                    intent.putExtra("no", "44")
                    startActivity(intent)
                    finish()
                }
            }
        }else if(stok44 > 0){
            no44.setOnClickListener {
                fiyat.setOnClickListener{
                    val result = db.stokAzalt(id, "44")
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
}