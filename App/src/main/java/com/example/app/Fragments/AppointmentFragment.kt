package com.example.app.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.Adapters.ApointmentsRecyclerAdapter
import com.example.app.Adapters.PrescriptionRecyclerAdapter
import com.example.app.ApiInterface
import com.example.app.Models.Appointments.Appointment
import com.example.app.Models.Appointments.RecyclerData
import com.example.app.Models.PrescriptionData
import com.example.app.R
import com.example.app.api.AppointmentResponse
import com.example.app.api.PrescriptionResponse
import com.example.app.databinding.FragmentAppointmentBinding
import com.example.app.databinding.FragmentPrescriptionBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppointmentFragment : Fragment() {

    private val makeAnAppointmentFragment: MakeAnAppointmentFragment =
        MakeAnAppointmentFragment.newInstance()
    var user_id: String? = null
    private lateinit var adapter: ApointmentsRecyclerAdapter
    private var _binding: FragmentAppointmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var btn_Make_An_Appointment: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val v = inflater.inflate(R.layout.fragment_appointment, container, false)

        _binding = FragmentAppointmentBinding.inflate(inflater, container, false)

        val appointmentList: ArrayList<RecyclerData> = ArrayList<RecyclerData>()
        val appointmentResponseList: ArrayList<RecyclerData> = ArrayList<RecyclerData>()


        // Calling API
        val apiInterface = ApiInterface.create().getUsersAppointments("616464bab36b1cf6f0da7f3c")
        apiInterface.enqueue(object : Callback<List<AppointmentResponse>> {

            @SuppressLint("SetTextI18n")
            override fun onResponse(
                call: Call<List<AppointmentResponse>>?,
                response: Response<List<AppointmentResponse>>?
            ) {


                // Getting response data
                if (response?.body() != null)
                    for (item in response!!.body()!!) {


                        appointmentResponseList.add(
                            RecyclerData(
                                item.doctor.name, item.doctor.name, item.date, item.time
                            )
                        )

                    }


                if (appointmentResponseList.size > 0) {

                    // Setting up tha data for the recuclerview
                    for (item in appointmentResponseList) {
                        appointmentList.add(item)
                    }

                }

                // Setting 'counter' Textview
                val counter = view?.findViewById<TextView>(com.example.app.R.id.counterText)
                if (counter != null) {
                    counter.text = "  Appointments: (" + appointmentResponseList.size + ")"
                }


                // Create View
                adapter = ApointmentsRecyclerAdapter(appointmentList)
                binding.recyclerView.layoutManager = LinearLayoutManager(context)
                binding.recyclerView.adapter = adapter


            }

            override fun onFailure(call: Call<List<AppointmentResponse>>?, t: Throwable?) {
                if (t != null) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }
            }
        })



        return binding.root

    }

    companion object {
        fun newInstance(): AppointmentFragment {
            return AppointmentFragment()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user_id = arguments?.getString("_id").toString()

        btn_Make_An_Appointment =
            view.findViewById<FloatingActionButton>(R.id.btn_Make_An_Appointment)
        btn_Make_An_Appointment.setOnClickListener {
            /*var transaction = getFragmentManager()?.beginTransaction()
            transaction?.replace(R.id.f_container, makeAnAppointmentFragment)
            transaction?.commit()*/

            val bundle = Bundle()
            bundle.putString("_id", user_id)
            makeAnAppointmentFragment.arguments = bundle

            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.f_container, makeAnAppointmentFragment)
            transaction?.commit()
        }
        var recycler = view.findViewById<RecyclerView>(R.id.recyclerView)

//
//        var scrollListener = object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, dy: Int) {
//                super.onScrollStateChanged(recyclerView, dy)
//                if (dy > 0) {
//                    btn_Make_An_Appointment.hide()
//                } else {
//                    btn_Make_An_Appointment.show()
//                }
//
//            }
//
//            recycler.addOnScrollListener(scrollListener)
//
//        }

    }

/*override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    user_id = arguments?.getString("_id").toString()
    println("AppointmentFragment check:")
    println(user_id)

    val btn_Make_An_Appointmet= view?.findViewById<Button>(R.id.btn_Make_An_Appointment)
    btn_Make_An_Appointmet?.setOnClickListener {
        var fr = getFragmentManager()?.beginTransaction()
        fr?.replace(R.id.f_container, MakeAnAppointmentFragment())
        fr?.commit()
    }
}*/

}