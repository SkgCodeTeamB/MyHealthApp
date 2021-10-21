package com.example.app.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.Models.PrescriptionData

class PrescriptionRecyclerAdapter(private val prescriptionData: List<PrescriptionData>) :
    RecyclerView.Adapter<PrescriptionRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.prescription_layout, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(prescriptionData[position])

    }

    override fun getItemCount(): Int {
        return prescriptionData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: PrescriptionData) {
            var doctorsName = itemView.findViewById<TextView>(R.id.item_SpecialityText)
            doctorsName.text = item.doctorsName

            var date = itemView.findViewById<TextView>(R.id.item_DateText)
            date.text = item.date

            var text = itemView.findViewById<TextView>(R.id.item_Text_field)
            text.text = item.text


        }


    }


}