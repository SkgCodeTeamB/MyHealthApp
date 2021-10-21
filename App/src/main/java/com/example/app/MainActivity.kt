package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.app.api.ApiInterface
import com.example.app.api.LoginResponse
import com.example.app.api.RegisterResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import com.google.gson.GsonBuilder

import com.google.gson.Gson
import okhttp3.OkHttpClient


class MainActivity : AppCompatActivity() {

    var loggedUser: LoginResponse? = null

    val BASE_URL = "http://192.168.1.5:5000/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val btn_Register: Button = findViewById(R.id.btn_Register)
        val btn_Login: Button = findViewById(R.id.btn_Login)
        val btn_API: Button = findViewById(R.id.btn_API)

        val editText_AMKA: EditText = findViewById(R.id.editText_AMKA)


        btn_API.setOnClickListener {
            val intent = Intent(this, TestApiActivity::class.java)
            startActivity(intent)
        }


        btn_Register.setOnClickListener {
            // your code to perform when the user clicks on the button
            val intentRegister = Intent(this, Register::class.java)
            startActivity(intentRegister)
        }

        btn_Login.setOnClickListener {

            if (editText_AMKA.text.toString().trim().isEmpty()) {
                editText_AMKA.error = "Required"
                //Toast.makeText(applicationContext, "AMKA is Required for Login", Toast.LENGTH_SHORT).show()
            } else {
                // After successful register you will move on new activity



                val gson = GsonBuilder()
                    .setLenient()
                    .create()


                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()


                val service = retrofit.create(ApiInterface::class.java)
                val call = service.loginUser(editText_AMKA.text.toString())

                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                        if (response.body() != null) {

                            val intentLogin = Intent(this@MainActivity, HomePage::class.java)
                            /*
                            intentLogin.putExtra("token", response.body()!!.token)
                            intentLogin.putExtra("_id", response.body()!!._id)
                            intentLogin.putExtra("name", response.body()!!.name)
                            intentLogin.putExtra("surname", response.body()!!.surname)
                            intentLogin.putExtra("email", response.body()!!.email)
                            intentLogin.putExtra("phone", response.body()!!.phone)
                            intentLogin.putExtra("birhtday", response.body()!!.birthday)
                            intentLogin.putExtra("bloodtype", response.body()!!.bloodtype)
                            intentLogin.putExtra("amka", response.body()!!.amka)
                            intentLogin.putExtra("familydoctor", response.body()!!.familydoctor)
                            intentLogin.putExtra("address", response.body()!!.address)
                            intentLogin.putExtra("city", response.body()!!.city)
                            intentLogin.putExtra("postalcode", response.body()!!.postalcode)
                            */

                            startActivity(intentLogin)
                            /*
                            Handler().postDelayed({
                                startActivity(Intent(this@MainActivity, HomePage::class.java).putExtra("token", response.body()!!.token))
                                startActivity(Intent(this@MainActivity, HomePage::class.java).putExtra("_id", response.body()!!._id))
                                startActivity(Intent(this@MainActivity, HomePage::class.java).putExtra("name", response.body()!!.name))
                                startActivity(Intent(this@MainActivity, HomePage::class.java).putExtra("surname", response.body()!!.surname))
                            }, 700)*/
                            Toast.makeText(this@MainActivity, "Your Login is successful.", Toast.LENGTH_SHORT).show()
                            //Toast.makeText(this@MainActivity, response.body()!!.name.toString(),Toast.LENGTH_SHORT).show()
/*
                            if (loggedUser != null){
                                loggedUser!!.token = response.body()!!.token
                                loggedUser!!._id = response.body()!!._id
                                loggedUser!!.name = response.body()!!.name
                                loggedUser!!.surname = response.body()!!.surname
                                loggedUser!!.email = response.body()!!.email
                                loggedUser!!.phone = response.body()!!.phone
                                loggedUser!!.birthday = response.body()!!.birthday
                                loggedUser!!.bloodtype = response.body()!!.bloodtype
                                loggedUser!!.amka = response.body()!!.amka
                                loggedUser!!.familydoctor = response.body()!!.familydoctor
                                loggedUser!!.address = response.body()!!.address
                                loggedUser!!.postalcode = response.body()!!.postalcode
                            }

                            */



                        }else {
                            Toast.makeText(this@MainActivity, "Wrong AMKA.Please try again", Toast.LENGTH_SHORT).show()
                        }


                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        if (t != null) {
                            Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                        }

                    }

                })

                /*
                    val intentLogin = Intent(this@MainActivity, HomePage::class.java)
                    startActivity(intentLogin)
                    Toast.makeText(this@MainActivity, "Your Login is successful.", Toast.LENGTH_SHORT).show()

                    */


            }
        }
    }

}
