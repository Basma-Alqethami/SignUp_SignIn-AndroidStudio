package com.example.signupandsignin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Home : AppCompatActivity() {

    private lateinit var tv_FullName: TextView
    private lateinit var tv_Mobile: TextView
    private lateinit var tv_address: TextView
    private lateinit var tv_EmailAddress: TextView

    private lateinit var btnLogOut: Button

    private val DB by lazy { Database(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        this.title = "Home"

        tv_FullName = findViewById(R.id.tv_FullName)
        tv_Mobile = findViewById(R.id.tv_Mobile)
        tv_address = findViewById(R.id.tv_address)
        tv_EmailAddress = findViewById(R.id.tv_EmailAddress)

        val disData = intent.getSerializableExtra("userData")
        val user = DB.ReadData(disData.toString())

        tv_EmailAddress.text = "Email: ${user.Email}"
        tv_FullName.text = "Welcome ${user.Name}"
        tv_Mobile.text = "Mobile: ${user.Mobile}"
        tv_address.text = "Address: ${user.Address}"

        btnLogOut = findViewById(R.id.btnLogOut)
        btnLogOut.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) }
    }
}