package com.example.app.Fragments

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.Models.PrescriptionData
import com.example.app.api.PrescriptionResponse

import com.example.app.databinding.FragmentPrescriptionBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import android.widget.TextView
import com.example.app.Adapters.DiagnosesRecyclerAdapter
import com.example.app.Adapters.PrescriptionRecyclerAdapter
import com.example.app.ApiInterface
import com.example.app.Models.DiagnosesData
import com.example.app.api.DiagnosesResponse
import com.example.app.databinding.FragmentDiagnosesBinding


class DiagnosesFragment : Fragment() {

    private lateinit var adapter: DiagnosesRecyclerAdapter
    private var _binding: FragmentDiagnosesBinding? = null
    private val binding get() = _binding!!
    var user_id: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDiagnosesBinding.inflate(inflater, container, false)

        val diagnosesList: ArrayList<DiagnosesData> = ArrayList<DiagnosesData>()
        val diagnosesResponseList: ArrayList<DiagnosesData> = ArrayList<DiagnosesData>()

        user_id = arguments?.getString("_id").toString()

    // Calling API
        val apiInterface = ApiInterface.create().getUsersDiagnoses(user_id)
        apiInterface.enqueue(object : Callback<List<DiagnosesResponse>> {

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<List<DiagnosesResponse>>?,
                response: Response<List<DiagnosesResponse>>?
            ) {

                // Getting response data
                if (response?.body() != null)
                    for (item in response!!.body()!!) {


                        diagnosesResponseList.add(
                            DiagnosesData(
                                "Doctor:  "+item.doctor.name,
                                item.date,
                                item.text
                            )
                        )

                    }


                if (diagnosesResponseList.size > 0) {

                    // Setting up tha data for the recuclerview
                    for (item in diagnosesResponseList) {
                        diagnosesList.add(item)
                    }

                }

                // Setting 'counter' Textview
                val counter = view?.findViewById<TextView>(com.example.app.R.id.counterText_diagnoses)
                if (counter != null) {
                    counter.text = "  Diagnoses: (" + diagnosesResponseList.size+")"
                }


                // Create View
                adapter = DiagnosesRecyclerAdapter(diagnosesList)
                binding.recyclerViewDiagnoses.layoutManager = LinearLayoutManager(context)
                binding.recyclerViewDiagnoses.adapter = adapter


            }

            override fun onFailure(call: Call<List<DiagnosesResponse>>?, t: Throwable?) {
                if (t != null) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            }
        })



        return binding.root
    }

    companion object {
        fun newInstance(): DiagnosesFragment {
            return DiagnosesFragment()
        }

    }


}
