package com.example.hackchallenge

import android.media.Image
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FeedPost(
    @Json(name = "name") val author : String,
    @Json(name = "username") val username : String,
    @Json(name = "message") val description : String,
    @Json(name = "liked_count") val numLikes : Int,
    @Json(name = "saved_count") val numSaves : Int
//    val profileImage : Int,
//    val postImage : Int
)

@JsonClass(generateAdapter = true)
data class FeedPosts(
    @Json(name = "posts") val posts : List<FeedPost>
)