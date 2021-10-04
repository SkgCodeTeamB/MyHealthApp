package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_Register : Button = findViewById(R.id.btn_Register)
        val btn_Login : Button = findViewById(R.id.btn_Login)

        val editText_AMKA : EditText = findViewById(R.id.editText_AMKA)

        btn_Register.setOnClickListener {
            // your code to perform when the user clicks on the button
            val intentRegister = Intent (this, Register::class.java)
            startActivity(intentRegister)
        }

        btn_Login.setOnClickListener{

            if(editText_AMKA.text.toString().trim().isEmpty()) {
                editText_AMKA.error = "Required"
                //Toast.makeText(applicationContext, "AMKA is Required for Login", Toast.LENGTH_SHORT).show()
            }
            else {
                // After successful register you will move on new activity
                val intentLogin = Intent(this, HomePage::class.java)
                startActivity(intentLogin)
                Toast.makeText(this@MainActivity, "Your Login is successful.", Toast.LENGTH_SHORT).show()
            }
        }


    }
}