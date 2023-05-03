package com.example.hackchallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

class mylistAdapter(private val mylist: List<Bucket>) :
    RecyclerView.Adapter<mylistAdapter.ViewHolder>() {
    private var completed=false

    class ViewHolder internal constructor(textView : View) : RecyclerView.ViewHolder(textView){

        var bucketname : TextView = textView.findViewById(R.id.mylist_name)
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
    }

    override fun getItemCount(): Int {
        return mylist.size
    }


}