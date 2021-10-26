package com.example.app.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.Models.Appointments.AppointmentData
import com.example.app.R

class AppointmentsRecyclerAdapter(private val appointmentData: List<AppointmentData>) :
    RecyclerView.Adapter<AppointmentsRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.apointment_layout, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: AppointmentsRecyclerAdapter.ViewHolder, position: Int) {
        holder.bindItem(appointmentData[position])
    }


    override fun getItemCount(): Int {
        return appointmentData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("CutPasteId")
        fun bindItem(item: AppointmentData) {


            var doctorsName = itemView.findViewById<TextView>(R.id.item_SpecialityText_field)
            doctorsName.text = item.doctor

            var date = itemView.findViewById<TextView>(R.id.item_Date_field)
            date.text = item.date

            var time = itemView.findViewById<TextView>(R.id.item_Time_field)
            time.text = item.time



        }


    }




}