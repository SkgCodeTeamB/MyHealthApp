package com.example.app.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.app.R

class AppointmentFragment : Fragment() {

    private val makeAnAppointmentFragment: MakeAnAppointmentFragment = MakeAnAppointmentFragment.newInstance()
    var user_id: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_appointment, container, false)
        return v
    }

    companion object {
        fun newInstance(): AppointmentFragment {
            return AppointmentFragment()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        user_id = arguments?.getString("_id").toString()

        val btn_Make_An_Appointment= view.findViewById<Button>(R.id.btn_Make_An_Appointment)
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