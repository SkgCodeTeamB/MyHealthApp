package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_Register : Button = findViewById(R.id.btn_Register)
        val btn_Login : Button = findViewById(R.id.btn_Login)


        btn_Register.setOnClickListener {
            // your code to perform when the user clicks on the button
            val intentRegister = Intent (this, Register::class.java)
            startActivity(intentRegister)
        }

    }
}