package com.example.app.Adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.Models.DiagnosesData
import com.example.app.R

class DiagnosesRecyclerAdapter(private val diagnosesData: List<DiagnosesData>) :
    RecyclerView.Adapter<DiagnosesRecyclerAdapter.ViewHolder>() {
    private var pos: Int = -1
    private val textList: ArrayList<TextView> = ArrayList<TextView>()
    private val cardList: ArrayList<CardView> = ArrayList<CardView>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.diagnoses_layout, parent, false)

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(diagnosesData[position])
    }

    override fun getItemCount(): Int {
        return diagnosesData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("CutPasteId")
        fun bindItem(item: DiagnosesData) {
            var doctorsName = itemView.findViewById<TextView>(R.id.item_from_diagnoses)
            doctorsName.text = item.doctorsName

            var date = itemView.findViewById<TextView>(R.id.item_DateText_diagnoses)
            date.text = item.date

            var text = itemView.findViewById<TextView>(R.id.item_Text_field_diagnoses)
            text.text = item.text

            var card = itemView.findViewById<CardView>(R.id.diagnoses_layout)

            // Saving Text and cardView color
            textList.add(text)
            cardList.add(card)

            // On click
            itemView.setOnClickListener {
                // Get current position
                val position: Int = adapterPosition


                // Check if the user has clicked a diagnose before
                if (pos == -1) {
                    // If this is the first time clicking a description to read

                    // Save clicked position
                    pos = position

                    // Set changes (More lines - bigger text size - different color)
                    text.maxLines = 10
                    text.textSize = 20.0F
                    card.setCardBackgroundColor(Color.parseColor("#F3F0F0"))

                } else if (pos == position) {
                    // If clicked diagnose is already in 'read mode'


                    // Set default settings (position - lines - text size - color)
                    pos = -1

                    text.maxLines = 2
                    text.textSize = 15.0F
                    card.setCardBackgroundColor(Color.parseColor("#FFFFFF"))

                } else {
                    //If users clicks more than one diagnoses

                    // Set clicked diagnose to 'read mode'
                    text.maxLines = 10
                    text.textSize = 20.0F
                    card.setCardBackgroundColor(Color.parseColor("#F3F0F0"))

                    // Set previous clicked diagnose to default settings
                    textList[pos].maxLines = 2
                    textList[pos].textSize = 15.0F
                    cardList[pos].setCardBackgroundColor(Color.parseColor("#FFFFFF"))


                    // update position
                    pos = position

                }

            }


        }


    }
}