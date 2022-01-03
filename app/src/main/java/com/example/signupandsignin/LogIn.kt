package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.io.IOException

class LogIn : AppCompatActivity() {

    private lateinit var btnLogIn: Button
    private lateinit var btnCreateAcounte: Button

    private lateinit var editTextTextEmailAddress: EditText
    private lateinit var editTextTextPassword: EditText

    private val DB by lazy { Database(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        this.title = "Log In"

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress)
        editTextTextPassword = findViewById(R.id.editTextTextPassword)

        btnLogIn = findViewById(R.id.btnLogIn)
        btnCreateAcounte = findViewById(R.id.btnCreateAcounte)

        btnCreateAcounte.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnLogIn.setOnClickListener { LogInUser()}
    }

    private fun LogInUser() {
        val textEmail = editTextTextEmailAddress.text.toString().lowercase()
        val textPassword = editTextTextPassword.text.toString()

        try {
            if (textEmail.isNotEmpty() && textPassword.isNotEmpty()) {
                if (textPassword.length >= 6) {
                    if (DB.CheckLogIn(textEmail,textPassword)) {
                        val intent = Intent(this, Home::class.java)
                        intent.putExtra("userData",textEmail)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this@LogIn, "email not exist or the password is wrong", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@LogIn, "Password must be greater than 6", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this@LogIn, "Please enter your email and password", Toast.LENGTH_SHORT).show()
            }
        }catch (e: IOException) {
            Toast.makeText(this@LogIn, "something wrong please try again", Toast.LENGTH_SHORT).show()
        }
    }
}