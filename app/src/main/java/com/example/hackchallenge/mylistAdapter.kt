package com.example.hackchallenge

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class mylistAdapter(private var mylist: List<Bucket>, private val onBucketChecked: (Bucket, Boolean) -> Unit) :
        RecyclerView.Adapter<mylistAdapter.ViewHolder>() {
    class ViewHolder internal constructor(textView : View) : RecyclerView.ViewHolder(textView){
        var completed=false
        var bucketname : TextView = textView.findViewById(R.id.mylist_name)
        var checkbox:CheckBox=textView.findViewById(R.id.complete_checkbox)
        var addbutton: Button =textView.findViewById(R.id.add_button)
        val PostButton : ImageButton = textView.findViewById(R.id.postButton)
        val bucketpic : ImageView = textView.findViewById(R.id.bucketImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bucket_cell_forlocal, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bucket = mylist[position]

//        holder.PostButton.setImageResource()
        holder.bucketname.text = bucket.name
//        holder.bucketpic.setImageResource()
        bucket.isComplete=holder.checkbox.isChecked
        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            holder.completed = isChecked
            onBucketChecked(bucket, isChecked) // call the callback function
        }

    }

    override fun getItemCount(): Int {
        return mylist.size
    }
    fun setItems(newItems: List<Bucket>) {
        mylist = newItems // assign the new list to the items variable
        notifyDataSetChanged()
    }

}