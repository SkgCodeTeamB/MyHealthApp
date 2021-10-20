package com.example.app.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.app.R

class AppointmentFragment : Fragment() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_appointment, container, false)

/*
        val btn_Make_An_Appointmet= view?.findViewById<Button>(R.id.btn_Make_An_Appointment)

        btn_Make_An_Appointmet?.setOnClickListener {
            val makeAnAppointmentFragment = MakeAnAppointmentFragment()


            val transaction : FragmentTransaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.f_container,makeAnAppointmentFragment)
            transaction.commit()
        }

*/
        return v
    }

    companion object {
        fun newInstance(): AppointmentFragment {
            return AppointmentFragment()
        }

    }
}