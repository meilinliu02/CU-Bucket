package com.example.hackchallenge

import android.media.Image

data class FeedPost(
    val author : String,
    val username : String,
//    val picture : Image,
    val location : String,
    val description : String,
    val numLikes : String,
    val numSaves : String
)
