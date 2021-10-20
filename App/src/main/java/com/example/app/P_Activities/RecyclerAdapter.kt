package com.example.dummyappointmentsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.dummyappointmentsapp.Models.Vaccination
import com.example.dummyappointmentsapp.Models.Vaccine

class RecyclerAdapter(
    private var vaccinesList: ArrayList<Vaccine>,
    private var vaccinationsList: ArrayList<Vaccination>
) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.vaccineName.text = vaccinesList[position].name
        for (v in vaccinationsList){
            if (v.vaccine.name == vaccinesList[position].name){
                holder.vaccinationDate.text = v.date
            }
        }
    }

    override fun getItemCount(): Int {
        return vaccinesList.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var vaccineName: TextView
        var vaccinationDate: TextView

        init {
            vaccineName = itemView.findViewById(R.id.vaccineName)
            vaccinationDate = itemView.findViewById(R.id.vaccinationDate)
        }
    }

}