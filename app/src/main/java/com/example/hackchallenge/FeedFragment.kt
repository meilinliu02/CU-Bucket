package com.example.hackchallenge

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
class SharedViewModel : ViewModel() {
    val newBucketItem = MutableLiveData<BucketItem>()

    fun addBucketItem(bucketItem: BucketItem) {
        newBucketItem.value = bucketItem
    }
}
class FeedFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setFragmentResultListener("requestKey") { requestKey, bundle ->
//            // We use a String here, but any type that can be put in a Bundle is supported,
//            // remember that putExtra from intents uses a Bundle underlying, so anything
//            // you putExtra works here too!
//            val result = bundle.getString("bundleKey")
//            // Do something with the result
//        }
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
//        val bucketFragment = fragmentManager?.findFragmentByTag("BucketFragment") as BucketFragment

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_feed, container, false)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        run()

        Log.d("loo", postsCache?.size.toString())

        val feedAdapter : FeedAdapter? = postsCache?.let { FeedAdapter(it,sharedViewModel) }
        val feedPosts : RecyclerView = view.findViewById(R.id.feed)
        feedPosts.adapter = feedAdapter
        val layoutManager = LinearLayoutManager(context)
        feedPosts.layoutManager = layoutManager
//        Log.d("feedfragment", sharedViewModel.newBucketItem.value.toString() )
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
