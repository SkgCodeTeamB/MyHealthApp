package com.example.app.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.ApiInterface
import com.example.app.R
import com.example.app.Models.Vaccinations.Vaccination
import com.example.app.Models.Vaccinations.Vaccine
import com.example.app.Adapters.VaccinationRecyclerAdapter
import retrofit2.Call
import retrofit2.Response

class VaccinesFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapterVaccination: RecyclerView.Adapter<VaccinationRecyclerAdapter.ViewHolder>? = null
    lateinit var recyclerView: RecyclerView

    lateinit var vaccine: Vaccine
    var vaccinesList = arrayListOf<Vaccine>()
    lateinit var vaccination: Vaccination
    var vaccinationsList = arrayListOf<Vaccination>()

    var user_id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vaccines, container, false)
    }

    companion object {
        fun newInstance(): VaccinesFragment {
            return VaccinesFragment()
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        user_id = arguments?.getString("_id").toString()

        val apiInterface = ApiInterface.create().getVaccines()
        apiInterface.enqueue( object : retrofit2.Callback<List<Vaccine>> {

            override fun onResponse(call: Call<List<Vaccine>>?, response: Response<List<Vaccine>>?) {
                if(response?.body() != null){
                    for (i in response.body()!!.indices) {
                        if(vaccinesList.size<11) {
                            vaccine = Vaccine(response.body()!![i]._id, response.body()!![i].name, response.body()!![i].id)
                            vaccinesList.add(vaccine)
                        }
                    }
                    loadVaccinationList()
                }
            }
            override fun onFailure(call: Call<List<Vaccine>>?, t: Throwable?) {
                println(t)
                Toast.makeText(activity, "Communication Problem", Toast.LENGTH_SHORT).show()
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
                Toast.makeText(activity, "Communication Problem", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun createView() {
        recyclerView = view?.findViewById(com.example.app.R.id.vaccinationRecyclerView)!!

        layoutManager = LinearLayoutManager(getActivity())
        recyclerView.layoutManager = layoutManager

        adapterVaccination = VaccinationRecyclerAdapter(vaccinesList, vaccinationsList)
        recyclerView.adapter = adapterVaccination
    }
}
