package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.app.api.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TestApiActivity : AppCompatActivity() {

    val BaseUrl = "http://192.168.0.199:5000/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_api)


        val btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener {
            getCurrentData() {

            }
        }

    }



    private fun getCurrentData(function: () -> Unit) {
        val test = findViewById<TextView>(R.id.responseText)
        test.text = null
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val service = retrofit.create(Hospital::class.java)
        val call = service.getHospitals()

        call.enqueue( object : Callback<List<HospitalResponseItem>>{
            override fun onResponse(call: Call<List<HospitalResponseItem>>?, response: Response<List<HospitalResponseItem>>?) {

                if (response?.body() != null)
                    //Toast.makeText(this@TestApiActivity, response.body().toString(), Toast.LENGTH_SHORT).show()
                    for (item in response!!.body()!!)
                        test.append(item.name + "\n")

            }

            override fun onFailure(call: Call<List<HospitalResponseItem>>?, t: Throwable?) {
                if (t != null) {
                    Toast.makeText(this@TestApiActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }


}