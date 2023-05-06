package com.example.hackchallenge

import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.internal.wait
import java.io.IOException

private val client = OkHttpClient()

var postsCache : List<FeedPost>? = null



fun run() {
    val request = Request.Builder()
        // URL given from API/Backend (make sure the URL + path
        // is corect for the given get request
        .url("http://34.145.174.171/api/posts/")
        .get()
        .build()


    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: java.io.IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                // Can also respond with some UI saying check internet, try again, network request failed,
                // etc. as opposed to exception (which will crash your app unless caught)!
                throw IOException("Unexpected code $response")
            } else {
                // Can get access to the body with response.body(). Can then use
                // Moshi techniques to convert said body to a Kotlin object if applicable
                // or you can just parse the body directly!
                val body = response.body

                val type = Types.newParameterizedType(
                    List::class.java, //List:class.java must come first!
                    FeedPost::class.java
                )
                val moshiInstance : Moshi = Moshi.Builder().build()

                val adapterPosts = moshiInstance.adapter<FeedPosts>(FeedPosts::class.java)
                val postArray : FeedPosts? = adapterPosts.fromJson(body!!.source())

                if (postArray != null) {
                    postsCache = postArray.posts
                }
            }
        }
    }
    )
}

fun runPost(description : String) {
    val post = FeedPost(
        author = "Bill",
        username = "Nye",
        description = description,
        numLikes = 10,
        numSaves = 15
    )

    val moshi: Moshi = Moshi.Builder().build()
    val adapter: JsonAdapter<FeedPost> =
        moshi.adapter<FeedPost>(FeedPost::class.java)
    val json: String = adapter.toJson(post)
    val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())


    val postRequest = Request.Builder()
        .post(requestBody)
        .url("http://34.145.174.171/api/users/1/posts/")
        // Can also add headers with .addHeader e.g .addHeader("Authorization", "Bearer ${TOKEN}")
        .build()


    client.newCall(postRequest).enqueue(object : Callback {
        override fun onFailure(call: Call, e: java.io.IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            if (!response.isSuccessful) {
                // Can also respond with some UI saying check internet, try again, network request failed,
                // etc. as opposed to exception (which will crash your app unless caught)!
                throw IOException("Unexpected code $response")
            } else {
                val body = response.body

                // Some APIs as a response to a POST request may return objects right back,
                // which we can then use Moshi to convert to a Kotlin object if
                // we wish (like the imgur example above)
                val blackjackHand = adapter.fromJson(body!!.source())

                // Other times, APIs will return a sucess/failure boolean as a response,
                // so parsing directly may a better alternative!
//                val jsonObject = JSONObject(body.string())
//                jsonObject.getString(KEY_NAME)
//                jsonObject.getInt(KEY_NAME)
//                ...
            }
        }
    })
}


