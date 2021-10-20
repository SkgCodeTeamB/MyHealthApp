package com.example.dummyappointmentsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.P_API.ApiInterface
import com.example.app.R
import com.example.dummyappointmentsapp.Models.Field
import com.example.dummyappointmentsapp.Models.Vaccination
import com.example.dummyappointmentsapp.Models.Vaccine
import retrofit2.Call
import retrofit2.Response

class VaccinationsActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null
    lateinit var recyclerView: RecyclerView

    lateinit var vaccine: Vaccine
    var vaccinesList = arrayListOf<Vaccine>()
    lateinit var vaccination: Vaccination
    var vaccinationsList = arrayListOf<Vaccination>()

    val user_id = "6166f4778ba0b351dec0d1ab"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaccinations)

        val apiInterface = ApiInterface.create().getVaccines()
        apiInterface.enqueue( object : retrofit2.Callback<List<Vaccine>> {

            override fun onResponse(call: Call<List<Vaccine>>?, response: Response<List<Vaccine>>?) {
                if(response?.body() != null){
                    for (i in response.body()!!.indices) {
                        vaccine = Vaccine(response.body()!![i]._id, response.body()!![i].name, response.body()!![i].id)
                        vaccinesList.add(vaccine)
                    }
                    loadVaccinationList()
                }
            }
            override fun onFailure(call: Call<List<Vaccine>>?, t: Throwable?) {
                println(t)
                Toast.makeText(this@VaccinationsActivity, "Communication Problem", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun loadVaccinationList() {
        val apiInterface = ApiInterface.create().getVaccinations(user_id)
        apiInterface.enqueue( object : retrofit2.Callback<List<Vaccination>> {

            override fun onResponse(call: Call<List<Vaccination>>?, response: Response<List<Vaccination>>?) {
                if(response?.body() != null){
                    for (i in response.body()!!.indices) {
                        vaccination = Vaccination(response.body()!![i]._id, response.body()!![i].date, response.body()!![i].user, response.body()!![i].vaccine)
                        vaccinationsList.add(vaccination)
                    }
                    createView()
                }
            }
            override fun onFailure(call: Call<List<Vaccination>>?, t: Throwable?) {
                println(t)
                Toast.makeText(this@VaccinationsActivity, "Communication Problem", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun createView() {
        recyclerView = findViewById(R.id.recyclerView)

        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = RecyclerAdapter(vaccinesList, vaccinationsList)
        recyclerView.adapter = adapter
    }
}