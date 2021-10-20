package com.example.app.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.Models.Vaccinations.Vaccination
import com.example.app.Models.Vaccinations.Vaccine

class VaccinationRecyclerAdapter(
    private var vaccinesList: ArrayList<Vaccine>,
    private var vaccinationsList: ArrayList<Vaccination>
) : RecyclerView.Adapter<VaccinationRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.vaccination_card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
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