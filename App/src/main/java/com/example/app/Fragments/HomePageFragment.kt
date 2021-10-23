package com.example.app.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.app.ApiInterface
import retrofit2.Call
import retrofit2.Response


class HomePageFragment : Fragment() {

    var appointments_counter: TextView? = null
    var prescriptions_counter: TextView? = null
    var diagnoses_counter: TextView? = null

    var user_id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.example.app.R.layout.fragment_homepage, container, false)
    }

    companion object {
        fun newInstance(): HomePageFragment {
            return HomePageFragment()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        user_id = arguments?.getString("_id").toString()
        showAppointmentsCounter()
        showPrescriptionsCounter()
        showDiagnosesCounter()
    }

    private fun showDiagnosesCounter() {
        diagnoses_counter = view?.findViewById(com.example.app.R.id.diagnoses_counter)

        var apiInterface = ApiInterface.create().getDiagnosesCounter(user_id)
        apiInterface.enqueue( object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {

                if(response?.body() != null){
                    diagnoses_counter?.text = response.body().toString()
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                println(t)
                Toast.makeText(activity, "Communication Problem", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showPrescriptionsCounter() {
        prescriptions_counter = view?.findViewById(com.example.app.R.id.prescriptions_counter)

        var apiInterface = ApiInterface.create().getPrescriptionsCounter(user_id)
        apiInterface.enqueue( object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {

                if(response?.body() != null){
                    prescriptions_counter?.text = response.body().toString()
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                println(t)
                Toast.makeText(activity, "Communication Problem", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showAppointmentsCounter() {
        appointments_counter = view?.findViewById(com.example.app.R.id.appointments_counter)

        var apiInterface = ApiInterface.create().getAppointmentsCounter(user_id)
        apiInterface.enqueue( object : retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>?, response: Response<String>?) {

                if(response?.body() != null){
                    appointments_counter?.text = response.body().toString()
                }
            }
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                println(t)
                Toast.makeText(activity, "Communication Problem", Toast.LENGTH_SHORT).show()
            }
        })
    }
}