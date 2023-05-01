package com.example.hackchallenge

import android.media.Image

data class FeedPost(
    val author : String,
    val username : String,
    val description : String,
    val numLikes : String,
    val numSaves : String,
    val profileImage : Int,
    val postImage : Int
)
