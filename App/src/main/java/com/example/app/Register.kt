package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val btn_Register2 : Button = findViewById(R.id.btn_Register2)


        val editText_Name : EditText = findViewById(R.id.editText_Name)
        val editText_Surname : EditText = findViewById(R.id.editText_Surname)
        val editText_AMKA2 : EditText = findViewById(R.id.editText_AMKA2)
        val editText_Email : EditText = findViewById(R.id.editText_Email)
        val editText_Phone : EditText = findViewById(R.id.editText_Phone)
        val editText_Birthday : EditText = findViewById(R.id.editText_Birthday)
        val spinner_BloodType : Spinner = findViewById(R.id.spinner_BloodType)
        val editText_FamilyDoctor : EditText = findViewById(R.id.editText_FamilyDoctor)
        val editText_Address : EditText = findViewById(R.id.editText_Address)
        val editText_City : EditText = findViewById(R.id.editText_City)
        val editText_PostalCode : EditText = findViewById(R.id.editText_PostalCode)



        btn_Register2.setOnClickListener {
            //check if the EditText have values or not
            if(editText_Name.text.toString().trim().isEmpty()) {
                editText_Name.error = "Required"
                Toast.makeText(applicationContext, "Name is Required", Toast.LENGTH_SHORT).show()
            }
            else if (editText_Surname.text.toString().trim().isEmpty()) {

                editText_Surname.error = "Required"
                Toast.makeText(applicationContext, "Surname is Required", Toast.LENGTH_SHORT).show()
            }
            else if (editText_AMKA2.text.toString().trim().isEmpty()) {
                editText_AMKA2.error = "Required"
                Toast.makeText(applicationContext, "AMKA is Required", Toast.LENGTH_SHORT).show()
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(editText_Email.text.toString().trim()).matches()){
                editText_Email.error = "Please enter a valid email address"
                Toast.makeText(applicationContext, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            }
            else if(editText_Phone.text.toString().trim().isEmpty()){
                editText_Phone.error = "Required"
                Toast.makeText(applicationContext, "Phone is Required", Toast.LENGTH_SHORT).show()
            }
            else if(editText_Birthday.text.toString().trim().isEmpty()){
                editText_Birthday.error = "Required"
                Toast.makeText(applicationContext, "Birthday is Required", Toast.LENGTH_SHORT).show()
            }
            /*
            else if(spinner_BloodType.get==0){
                //spinner_BloodType.error = "Required"
                Toast.makeText(applicationContext, "Blood Type is Required", Toast.LENGTH_SHORT).show()
            }
            */
            else if(editText_FamilyDoctor.text.toString().trim().isEmpty()){
                editText_FamilyDoctor.error = "Required"
                Toast.makeText(applicationContext, "Family DOctor Type is Required", Toast.LENGTH_SHORT).show()
            }
            else {
                // After successful register you will move on new activity
                val intentRegister = Intent(this, MainActivity::class.java)
                startActivity(intentRegister)
                Toast.makeText(this@Register, "Your Register is successful.", Toast.LENGTH_SHORT).show()

            }


        }



    }




}