package com.example.hackchallenge

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FeedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feed, container, false)
        val feedDataset = listOf(
            FeedPost(
                author = "Martha",
                username = "martha123",
                description = "party",
                numLikes = "4",
                numSaves = "2",
                profileImage = R.drawable.squid,
                postImage = R.drawable.squid
            ),
            FeedPost(
                author = "ben",
                username = "ben_is_awesome",
                description = "I am looking to go on a hike in the area with a couple of people",
                numLikes = "2",
                numSaves = "4",
                profileImage = R.drawable.squid,
                postImage = R.drawable.squid
            )
        )

        Log.d("hello", "w")

        val feedAdapter : FeedAdapter = FeedAdapter(feedDataset)

        val feedPosts : RecyclerView = view.findViewById(R.id.feed)

        feedPosts.adapter = feedAdapter

        val layoutManager = LinearLayoutManager(context)
        feedPosts.layoutManager = layoutManager




        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FeedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String = "", param2: String = "'") =
            FeedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}