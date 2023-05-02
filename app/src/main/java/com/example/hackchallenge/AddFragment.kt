package com.example.hackchallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class AddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var bucketAdapter: BucketItemAdapter
//    private lateinit var recyclerView: RecyclerView
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
        val bucketList = listOf(BucketItem("See a purple sunset","description1",false),
              BucketItem("Rock climb @Noyes","description2",false),
              BucketItem("Scale the clock tower","description3",false),
              BucketItem("Make ramen in Uris","description4",false),
              BucketItem("Etc..","description4",false),
              BucketItem("Etc..","description4",false)
        )

       bucketAdapter =BucketItemAdapter(bucketList)

        val addPosts : RecyclerView = view.findViewById(R.id.feed)

        addPosts.adapter = bucketAdapter

        val layoutManager = LinearLayoutManager(context)
        addPosts.layoutManager = layoutManager

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