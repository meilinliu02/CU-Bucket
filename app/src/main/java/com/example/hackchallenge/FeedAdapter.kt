package com.example.hackchallenge

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FeedAdapter(private val feed : List<FeedPost>) :
    RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    class ViewHolder internal constructor(textView : View) : RecyclerView.ViewHolder(textView){
        val feedAuthor : TextView = textView.findViewById(R.id.name)
        val feedUsername : TextView = textView.findViewById(R.id.username)
        val feedDesc : TextView = textView.findViewById(R.id.description)
        val feedLikes : TextView = textView.findViewById(R.id.likes)
        val feedSaves : TextView = textView.findViewById(R.id.saves)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.feed_cell, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feedPost = feed[position]
        holder.feedAuthor.text = feedPost.author
        holder.feedDesc.text = feedPost.description
        holder.feedLikes.text = feedPost.numLikes
        holder.feedSaves.text = feedPost.numSaves
        holder.feedUsername.text = feedPost.username
    }

    override fun getItemCount(): Int {
        return feed.size
    }


}