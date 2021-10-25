package com.example.app.Adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.Models.Appointments.Appointment
import com.example.app.Models.Appointments.RecyclerData
import com.example.app.R
import com.example.app.Models.PrescriptionData

class ApointmentsRecyclerAdapter(private val appointmentData: List<RecyclerData>) :
    RecyclerView.Adapter<ApointmentsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.apointment_layout, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ApointmentsRecyclerAdapter.ViewHolder, position: Int) {
        holder.bindItem(appointmentData[position])
    }


    override fun getItemCount(): Int {
        return appointmentData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("CutPasteId")
        fun bindItem(item: RecyclerData) {

            var specialityField = itemView.findViewById<TextView>(R.id.item_SpecialityText_field)
            specialityField.text = item.speciallity

            var doctorsName = itemView.findViewById<TextView>(R.id.item_DoctorText_field)
            doctorsName.text = item.doctor

            var date = itemView.findViewById<TextView>(R.id.item_Date_field)
            date.text = item.date

            var time = itemView.findViewById<TextView>(R.id.item_Time_field)
            time.text = item.time



        }


    }




}