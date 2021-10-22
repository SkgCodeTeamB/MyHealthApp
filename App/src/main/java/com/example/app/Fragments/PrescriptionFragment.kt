package com.example.app.Fragments

import android.os.Bundle
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
import com.example.app.Adapters.PrescriptionRecyclerAdapter
import com.example.app.ApiInterface


class PrescriptionFragment : Fragment() {

    private lateinit var adapter: PrescriptionRecyclerAdapter
    private var _binding: FragmentPrescriptionBinding? = null
    private val binding get() = _binding!!
    private val prescriptionResponseList = ArrayList<PrescriptionData>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPrescriptionBinding.inflate(inflater, container, false)

        val prescriptionList = ArrayList<PrescriptionData>()

        // Calling API
        val apiInterface = ApiInterface.create().getPrescriptions()
        apiInterface.enqueue(object : Callback<List<PrescriptionResponse>> {

            override fun onResponse(
                call: Call<List<PrescriptionResponse>>?,
                response: Response<List<PrescriptionResponse>>?
            ) {

                // Getting response data
                if (response?.body() != null)
                    for (item in response!!.body()!!) {

                        prescriptionResponseList.add(
                            PrescriptionData(
                                item.doctor.name,
                                item.date,
                                item.text
                            )
                        )

                    }


                if (prescriptionResponseList.size > 0) {

                    for (item in prescriptionResponseList) {

                        //Toast.makeText(context, item.text + "    " + item.date, Toast.LENGTH_SHORT).show()
                        prescriptionList.add(item)

                    }

                }


                adapter = PrescriptionRecyclerAdapter(prescriptionList)

                binding.recyclerView.layoutManager = LinearLayoutManager(context)

                binding.recyclerView.adapter = adapter

                // Setting counter Textview
                var counter = view?.findViewById<TextView>(com.example.app.R.id.counterText)
                if (counter != null) {
                    counter.text = "Counter: " + prescriptionResponseList.size
                }
            }

            override fun onFailure(call: Call<List<PrescriptionResponse>>?, t: Throwable?) {
                if (t != null) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            }
        })



        return binding.root
    }

    companion object {
        fun newInstance(): PrescriptionFragment {
            return PrescriptionFragment()
        }

    }


}
