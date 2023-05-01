package com.example.hackchallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FeedAdapter(private val feed : List<FeedPost>) :
    RecyclerView.Adapter<FeedAdapter.ViewHolder>() {


    class ViewHolder internal constructor(textView : View) : RecyclerView.ViewHolder(textView){
        var feedAuthor : TextView = textView.findViewById(R.id.name)
        var feedUsername : TextView = textView.findViewById(R.id.username)
        var feedDesc : TextView = textView.findViewById(R.id.description)
        var feedLikes : TextView = textView.findViewById(R.id.likes)
        var feedSaves : TextView = textView.findViewById(R.id.saves)
        val likeButton : ImageButton = textView.findViewById(R.id.likeButton)
        val saveButton : ImageButton = textView.findViewById(R.id.saveButton)
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
        holder.likeButton.setOnClickListener{
            holder.feedLikes.text = (holder.feedLikes.text.toString().toInt() + 1).toString()
            holder.likeButton.setImageResource(androidx.appcompat.R.drawable.abc_star_black_48dp)
        }
        holder.saveButton.setOnClickListener{
            holder.feedSaves.text = (holder.feedSaves.text.toString().toInt() + 1).toString()
            holder.saveButton.setImageResource(androidx.appcompat.R.drawable.abc_star_black_48dp)
        }
    }

    override fun getItemCount(): Int {
        return feed.size
    }


}