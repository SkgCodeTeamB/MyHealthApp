package com.example.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dummyappointmentsapp.Models.LoginInfo
import com.example.dummyappointmentsapp.Models.LoginResponse
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var loggedUser: LoginResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText_AMKA: EditText = findViewById(R.id.editText_AMKA)
        val btn_Register: Button = findViewById(R.id.btn_Register)
        val btn_Login: Button = findViewById(R.id.btn_Login)

        btn_Register.setOnClickListener {
            val intentRegister = Intent(this, Register::class.java)
            startActivity(intentRegister)
        }

        btn_Login.setOnClickListener {

            if (editText_AMKA.text.toString().trim().isEmpty()) {
                editText_AMKA.error = "Required"
            } else {
                val userAMKA = editText_AMKA.text.toString()
                val loginInfo = LoginInfo(userAMKA)     //create object for login request

                var apiInterface = ApiInterface.create().login(loginInfo)
                apiInterface.enqueue( object : retrofit2.Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {

                        if(response?.body() != null){
                            loggedUser = LoginResponse(response.body()!!.token, response.body()!!.user)     //create object with token and logged user's information
                            //println(loggedUser.token)
                            //println(loggedUser.user.surname)
                            val intentLogin = Intent(this@MainActivity, HomePage::class.java)
                            intentLogin.putExtra("_ID", loggedUser!!.user._id)
                            startActivity(intentLogin)
                            Toast.makeText(this@MainActivity, "Successful Login", Toast.LENGTH_SHORT).show()
                        }else {
                            Toast.makeText(this@MainActivity, "Wrong AMKA.Please try again", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                        println(t)
                        Toast.makeText(this@MainActivity, "Communication Problem", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }

}
