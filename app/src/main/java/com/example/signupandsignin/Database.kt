package com.example.signupandsignin

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database (context: Context): SQLiteOpenHelper(context,"Users.db", null, 1) {

    private val sqLiteDatabase: SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE users_table (Id INTEGER PRIMARY KEY AUTOINCREMENT, Email TEXT NOT NULL, Password TEXT NOT NULL, Name TEXT, Mobile TEXT, Address TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users_table")
        onCreate(db)
    }

    fun saveData(email: String, password: String, name: String, mobile: String, address: String){
        val contentValues = ContentValues()
        contentValues.put("Email", email)
        contentValues.put("Password", password)
        contentValues.put("Name", name)
        contentValues.put("Mobile", mobile)
        contentValues.put("Address", address)
        sqLiteDatabase.insert("users_table", null, contentValues)
    }


    @SuppressLint("Range")
    fun ReadData(email: String): User {
        var c: Cursor = sqLiteDatabase.query(
            "users_table", null, "Email=?", arrayOf(email), null, null, null
        )
        c.moveToFirst()
        val password = c.getString(c.getColumnIndex("Password"))
        val name = c.getString(c.getColumnIndex("Name"))
        val mobile = c.getString(c.getColumnIndex("Mobile"))
        val address = c.getString(c.getColumnIndex("Address"))
        val id = c.getInt(c.getColumnIndex("Id"))

        return User(id,email,password,name,mobile,address)
    }

    fun checkUser(email: String): Boolean {
        val c: Cursor = sqLiteDatabase.query(
                "users_table", null, "Email=?", arrayOf(email), null, null, null
            )
        if (c.count < 1) {
            return true
        }
        return false
    }

    @SuppressLint("Range")
    fun CheckLogIn(email: String, entryPassword: String): Boolean {

        var c: Cursor = sqLiteDatabase.query(
            "users_table", null, "Email=?", arrayOf(email), null, null, null
        )
        if (c.count < 1) {
            return false
        }
        c.moveToFirst()
        var password = c.getString(c.getColumnIndex("Password"))
        if (entryPassword == password) {
        return true
        }
        return false
    }
}