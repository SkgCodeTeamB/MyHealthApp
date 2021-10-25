package com.example.app

import android.app.DatePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Toast
import com.example.app.api.RegisterResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class Register : AppCompatActivity() {

    //val BASE_URL = "http://192.168.1.5:5000/"
    val BASE_URL = "http://192.168.0.199:5000/"

    private var mDisplayDate: TextView? = null
    private var mDateSetListener: DatePickerDialog.OnDateSetListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        val btn_Register2 : Button = findViewById(R.id.btn_Register2)


        val editText_Name : EditText = findViewById(R.id.editText_Name)
        val editText_Surname : EditText = findViewById(R.id.editText_Surname)
        val editText_AMKA2 : EditText = findViewById(R.id.editText_AMKA2)
        val editText_Email : EditText = findViewById(R.id.editText_Email)
        val editText_Phone : EditText = findViewById(R.id.editText_Phone)
        //val editText_Birthday : EditText = findViewById(R.id.editText_Birthday)
        val spinner_BloodType : Spinner = findViewById(R.id.spinner_BloodType)
        val spinner_FamilyDoctor : Spinner = findViewById(R.id.spinner_FamilyDoctor)
        val editText_Address : EditText = findViewById(R.id.editText_Address)
        val editText_City : EditText = findViewById(R.id.editText_City)
        val editText_PostalCode : EditText = findViewById(R.id.editText_PostalCode)


        //Birthday Dialog
        mDisplayDate = findViewById(R.id.editText_Birthday) as EditText?
        mDisplayDate!!.setOnClickListener {
            val cal = Calendar.getInstance()
            val year = cal[Calendar.YEAR]
            val month = cal[Calendar.MONTH]
            val day = cal[Calendar.DAY_OF_MONTH]
            val dialog = DatePickerDialog(
                this@Register,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                mDateSetListener,
                year, month, day
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()
        }
        mDateSetListener =
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                var month = month
                month = month + 1
                Log.d(
                    TAG,
                    "onDateSet: mm/dd/yyy: $day/$month/$year"
                )
                val date = "$day/$month/$year"
                mDisplayDate!!.text = date
            }



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

            else if((mDisplayDate as EditText).text.toString().trim().isEmpty()){
                (mDisplayDate as EditText).error = "Required"
                Toast.makeText(applicationContext, "Birthday is Required", Toast.LENGTH_SHORT).show()
            }

            else if(spinner_BloodType.getSelectedItem().toString() == "Blood Type"){
                //spinner_BloodType.
                Toast.makeText(applicationContext, "Blood Type is Required", Toast.LENGTH_SHORT).show()
            }

            else {




                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()


                val service = retrofit.create(ApiInterface::class.java)
                val call = service.addUser(
                    editText_Name.text.toString(),
                    editText_Surname.text.toString(),
                    editText_Email.text.toString(),
                    editText_Phone.text.toString(),
                    (mDisplayDate as EditText).text.toString(),
                    spinner_BloodType.getSelectedItem().toString(),
                    editText_AMKA2.text.toString(),
                    spinner_FamilyDoctor.getSelectedItem().toString(),
                    editText_Address.text.toString(),
                    editText_City.text.toString(),
                    editText_PostalCode.text.toString())

                call.enqueue( object : Callback<RegisterResponse>{
                    override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {

                        if (response.code() == 200) {
                                Toast.makeText(this@Register, "Your Register is successful.", Toast.LENGTH_SHORT).show()
                                val intentRegister = Intent(this@Register, MainActivity::class.java)
                                startActivity(intentRegister)
                            } else{
                                Toast.makeText(this@Register, "An error occurred please try again later...", Toast.LENGTH_SHORT).show()
                            }

                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        if (t != null) {
                            Toast.makeText(this@Register, t.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                })
                    /*
                       val intentRegister = Intent(this@Register, MainActivity::class.java)
                       startActivity(intentRegister)
                       Toast.makeText(this@Register, "Your Register is successful.", Toast.LENGTH_SHORT).show()
                    */

            }





        }


    }







}


