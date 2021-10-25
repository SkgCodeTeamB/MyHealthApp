package com.example.app.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.app.Models.Appointments.Appointment
import com.example.app.R
import com.example.dummyappointmentsapp.Models.User

class PersonalDataFragment : Fragment() {

    var user_id : String? = null

    var txt_Name: TextView? = null
    var txt_Surname: TextView? = null
    var txt_Birthday: TextView? = null
    var txt_AMKA: TextView? = null
    var txt_BloodType: TextView? = null
    var txt_FamilyDoctor: TextView? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personaldata, container, false)
    }

    companion object {
        fun newInstance(): PersonalDataFragment {
            return PersonalDataFragment()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        txt_Name = view?.findViewById(com.example.app.R.id.txt_Name)
        txt_Surname = view?.findViewById(com.example.app.R.id.txt_Surname)
        txt_Birthday = view?.findViewById(com.example.app.R.id.txt_Birthday)
        txt_AMKA = view?.findViewById(com.example.app.R.id.txt_AMKA)
        txt_BloodType = view?.findViewById(com.example.app.R.id.txt_BlooType)
        txt_FamilyDoctor = view?.findViewById(com.example.app.R.id.txt_FamilyDoctor)

        user_id = arguments?.getString("_id").toString()

        //val user = User()

    }


}