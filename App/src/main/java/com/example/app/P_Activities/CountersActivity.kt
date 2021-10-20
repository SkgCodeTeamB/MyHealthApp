package com.example.dummyappointmentsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.app.P_API.ApiInterface
import com.example.app.R
import com.example.dummyappointmentsapp.Models.Doctor
import retrofit2.Call
import retrofit2.Response

class CountersActivity : AppCompatActivity() {

    var appointments_counter: TextView? = null
    var prescriptions_counter: TextView? = null
    var diagnoses_counter: TextView? = null

    var user_id = "6166f4778ba0b351dec0d1ab"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counters)

        appointments_counter = findViewById(R.id.appointments_counter)
        prescriptions_counter = findViewById(R.id.prescriptions_counter)
        diagnoses_counter = findViewById(R.id.diagnoses_counter)

        var apiInterface = ApiInterface.create().getAppointmentsCounter(user_id)
        apiInterface.enqueue( object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {

                if(response?.body() != null){
                    appointments_counter?.text = response.body().toString()
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                println(t)
                Toast.makeText(this@CountersActivity, "Communication Problem", Toast.LENGTH_SHORT).show()
            }
        })

        apiInterface = ApiInterface.create().getPrescriptionsCounter(user_id)
        apiInterface.enqueue( object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {

                if(response?.body() != null){
                    prescriptions_counter?.text = response.body().toString()
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                println(t)
                Toast.makeText(this@CountersActivity, "Communication Problem", Toast.LENGTH_SHORT).show()
            }
        })

        apiInterface = ApiInterface.create().getDiagnosesCounter(user_id)
        apiInterface.enqueue( object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {

                if(response?.body() != null){
                    diagnoses_counter?.text = response.body().toString()
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                println(t)
                Toast.makeText(this@CountersActivity, "Communication Problem", Toast.LENGTH_SHORT).show()
            }
        })
    }
}