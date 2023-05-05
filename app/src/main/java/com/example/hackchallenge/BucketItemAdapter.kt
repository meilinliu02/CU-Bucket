package com.example.hackchallenge

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class BucketItemAdapter(
    private var items: List<BucketItem>,
    private val onBucketChecked: (BucketItem, Boolean) -> Unit
) : RecyclerView.Adapter<BucketItemAdapter.ViewHolder>() {
     inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkbox:CheckBox=itemView.findViewById(R.id.complete_checkbox)
        val nameTextView: TextView = itemView.findViewById(R.id.name_textview)
//        val descriptionTextView: TextView = itemView.findViewById(R.id.description_textview)
    }
    fun filter(query: String) {
        var list= arrayListOf<BucketItem>()
        if (query.isEmpty()) {
        } else {
            for (item in items) {
                if (item.name?.toLowerCase(Locale.ROOT)?.contains(query.toLowerCase(Locale.ROOT)) == true) {
                    list.addAll(listOf(item))
                    items=list
                }
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.bucket_cell, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.name
        holder.checkbox.isChecked = item.isComplete
        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            item.isComplete = isChecked
            onBucketChecked(item, isChecked)
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(filteredList: List<BucketItem>) {
        items = filteredList // assign the new list to the items variable
        notifyDataSetChanged()
    }

}
