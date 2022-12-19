package com.semayalcin.ayakkabisatisuyg

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DataBase(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "ayakkabisatis.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val table1 = " CREATE TABLE ayakkabilar(" +
                "ayakkabi_id INTEGER, " +
                "ayakkabi_adi TEXT, " +
                "fiyat INTEGER, " +
                "ayakkabi_bedeni TEXT, " +
                "magaza_stok INTEGER, " +
                "merkez_magaza_stok INTEGER)"

        val table2 = " CREATE TABLE satici(" +
                "satis_danismani_id INTEGER, " +
                "satis_danismani_ciro INTEGER)"

        db?.execSQL(table1)
        db?.execSQL(table2)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        /*db.execSQL("drop table if exists ayakkabilar")
        onCreate(db)*/
    }
    /*fun deleteAllData(){
        val sqliteDB = this.writableDatabase
        sqliteDB.delete("ayakkabilar",null,null)
        sqliteDB.close()
    }*/

    @SuppressLint("Range")
    fun read(id:Int): MutableList<User>{
        val userList = mutableListOf<User>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("select * from ayakkabilar WHERE ayakkabi_id =$id", null)
        if(cursor.moveToFirst()){
            do {
                val user = User()
                user.ayakkabiadi = cursor.getString(cursor.getColumnIndex("ayakkabi_adi"))
                user.fiyat = cursor.getInt(cursor.getColumnIndex("fiyat"))
                userList.add(user)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userList
    }

    @SuppressLint("Range")
    fun readciro(): MutableList<Satici>{
        val saticiList = mutableListOf<Satici>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("select * from satici", null)
        if(cursor.moveToFirst()){
            do {
                val satici = Satici()
                satici.saticiId = cursor.getInt(cursor.getColumnIndex("satis_danismani_id"))
                satici.saticiCiro = cursor.getInt(cursor.getColumnIndex("satis_danismani_ciro"))
                saticiList.add(satici)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return saticiList
    }




    @SuppressLint("Range")
    fun readStok(id: Int, beden:String): Int {
        val db = this.readableDatabase
        var stok = 0
        val cursor = db.rawQuery("SELECT magaza_stok FROM ayakkabilar where ayakkabi_id = $id and ayakkabi_bedeni = $beden", null)
        if(cursor.moveToFirst()){
            do {
                stok = cursor.getInt(cursor.getColumnIndex("magaza_stok"))
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return stok
    }

    @SuppressLint("Range")
    fun readMstok(id:Int, no:String): MutableList<Mstok> {
        val db = this.readableDatabase
        val list = mutableListOf<Mstok>()
        val cursor = db.rawQuery("SELECT * FROM ayakkabilar where ayakkabi_id = $id and ayakkabi_bedeni = $no", null)
        if(cursor.moveToFirst()){
            do {
                val mstok = Mstok()
                mstok.stok = cursor.getInt(cursor.getColumnIndex("merkez_magaza_stok"))
                mstok.fiyat = cursor.getInt(cursor.getColumnIndex("fiyat"))
                list.add(mstok)
            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }

    fun insertData(data: Data): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("ayakkabi_id", data.ayakkabiid)
        values.put("ayakkabi_adi", data.ayakkabiadi)
        values.put("fiyat", data.fiyat)
        values.put("ayakkabi_bedeni", data.no)
        values.put("magaza_stok", data.stok)
        values.put("merkez_magaza_stok", data.mstok)

        val result = db.insert("ayakkabilar", null, values)
        db.close()
        return result
    }

    fun insertSatici(id:Int, fiyat:Int): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put("satis_danismani_id", id)
        values.put("satis_danismani_ciro", fiyat)
        val result = db.insert("satici", null, values)
        db.close()
        return result
    }

    @SuppressLint("Range")
    fun update(id: Int, no: String, adet: Int): Boolean {
        val db = this.readableDatabase
        db.execSQL("UPDATE ayakkabilar SET magaza_stok = magaza_stok+ $adet  WHERE ayakkabi_id = $id  AND ayakkabi_bedeni = $no")
        return true
    }

    fun saticiCiro(fiyat: Int, id: Int): Boolean {
        val db = this.readableDatabase
        db.execSQL("UPDATE satici SET satis_danismani_ciro = satis_danismani_ciro + $fiyat WHERE satis_danismani_id = $id")
        return true
    }

    fun stokAzalt(id: Int, no:String): Boolean {
        val db = this.readableDatabase
        db.execSQL("UPDATE ayakkabilar SET magaza_stok = magaza_stok-1  WHERE ayakkabi_id = $id  AND ayakkabi_bedeni = $no")
        return true
    }
    fun mstokAzalt(id: Int, no:String): Boolean {
        val db = this.readableDatabase
        db.execSQL("UPDATE ayakkabilar SET merkez_magaza_stok = merkez_magaza_stok-1  WHERE ayakkabi_id = $id  AND ayakkabi_bedeni = $no")
        return true
    }


}

