package com.example.hackchallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BucketItemAdapter(private val items: List<BucketItem>) : RecyclerView.Adapter<BucketItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name_textview)
//        val descriptionTextView: TextView = itemView.findViewById(R.id.description_textview)
        val completeCheckBox: CheckBox = itemView.findViewById(R.id.complete_checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bucket_cell, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.name
//        holder.descriptionTextView.text = item.description
        holder.completeCheckBox.isChecked = item.isComplete
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
