package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btn_Register2 : Button = findViewById(R.id.btn_Register2)


        val editText_Name : EditText = findViewById(R.id.editText_Name)
        val editText_Surname : EditText = findViewById(R.id.editText_Surname)
        val editText_AMKA : EditText = findViewById(R.id.editText_AMKA)


        btn_Register2.setOnClickListener {
            //check if the EditText have values or not
            if(editText_Name.text.toString().trim().isEmpty()) {
                editText_Name.error = "Required"
                Toast.makeText(applicationContext, "Name is Required ", Toast.LENGTH_SHORT).show()
            }
            else if (editText_Surname.text.toString().trim().isEmpty()) {
                editText_Surname.error = "Required"
                Toast.makeText(applicationContext, "Surname is Required ", Toast.LENGTH_SHORT).show()
            }
            else if (editText_AMKA.text.toString().trim().isEmpty()) {
                editText_AMKA.error = "Required"
                Toast.makeText(applicationContext, "AMKA is Required ", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this@Register, "Your Register is successful.", Toast.LENGTH_SHORT)
                    .show()

                // After successful register you will move on new activity
                val intentRegister = Intent(this, MainActivity::class.java)
                startActivity(intentRegister)
            }


        }


    }
}