package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.IOException

class SignUp : AppCompatActivity() {

    private lateinit var btnSignUp: Button
    private lateinit var btnLog: Button

    private lateinit var et_Name: EditText
    private lateinit var et_Mobile: EditText
    private lateinit var et_address: EditText
    private lateinit var et_EmailAddress: EditText
    private lateinit var et_Password: EditText

    private val DB by lazy { Database(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        this.title = "Sign Up"

        et_Name = findViewById(R.id.et_Name)
        et_Mobile = findViewById(R.id.et_Mobile)
        et_address = findViewById(R.id.et_address)
        et_EmailAddress = findViewById(R.id.et_EmailAddress)
        et_Password = findViewById(R.id.et_Password)

        btnSignUp = findViewById(R.id.btnSignUp)
        btnLog = findViewById(R.id.btnLog)

        btnLog.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }

        btnSignUp.setOnClickListener { SaveData()}
    }

    private fun SaveData() {
        val textName = et_Name.text.toString()
        val textEmail = et_EmailAddress.text.toString().lowercase()
        val textPasswrd = et_Password.text.toString()
        val textAddress = et_address.text.toString()
        val textMobile = et_Mobile.text.toString()

        try {
            if (textEmail.isNotEmpty() && textPasswrd.isNotEmpty()) {
                if (DB.checkUser(textEmail)) {
                    if (textPasswrd.length >= 6) {

                        DB.saveData(textEmail, textPasswrd, textName, textMobile, textAddress)
                        val intent = Intent(this, Home::class.java)
                        intent.putExtra("userData",textEmail)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this@SignUp, "Password must be greater than 6", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@SignUp, "The email is already exist", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@SignUp, "Please enter all information", Toast.LENGTH_SHORT).show()
            }
        }catch (e: IOException) {
            Toast.makeText(this@SignUp, "something wrong please try again", Toast.LENGTH_SHORT).show()
        }
    }
}