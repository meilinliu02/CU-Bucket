package com.example.hackchallenge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FeedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class BucketFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var bucketAdapter: BucketItemAdapter
    private lateinit var emptybutton:Button
    private lateinit var fullbutton:Button
    private lateinit var allbutton:Button
    private lateinit var searchview:SearchView
    private val sharedViewModel: SharedViewModel by activityViewModels()
    var bucketList = mutableListOf(
        BucketItem("See a purple sunset", "description1", false),
        BucketItem("Rock climb @Noyes", "description2", false),
        BucketItem("Scale the clock tower", "description3", false),
    )
    private var filteredList = bucketList
    private var searchQuery = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("newBucketItemRequestKey") { _, bundle ->
            val newBucketItem = bundle.getParcelable<BucketItem>("newBucketItem")
        }
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
//            val result = "result"
//            setFragmentResult("requestKey",
//                bundleOf("bundleKey" to result))
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mylist, container, false)
        emptybutton=view.findViewById(R.id.emptyBucketButton)
        fullbutton=view.findViewById(R.id.fullBucketButton)
        allbutton=view.findViewById(R.id.allBucketButton)
        searchview=view.findViewById(R.id.search_bucketlist)
        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // do nothing
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchQuery = newText
                    bucketAdapter.filter(searchQuery)
                }
                return true
            }
        })
        bucketAdapter = BucketItemAdapter(filteredList) { item, isChecked ->item.isComplete=isChecked }
        emptybutton.setOnClickListener {
            filteredList = bucketList.filter { !it.isComplete } as MutableList<BucketItem>
            bucketAdapter.setItems(filteredList)


        }
        fullbutton.setOnClickListener {
            filteredList = bucketList.filter { it.isComplete } as MutableList<BucketItem>
            bucketAdapter.setItems(filteredList)

        }
        allbutton.setOnClickListener {
            bucketAdapter.setItems(bucketList)

        }
        sharedViewModel.newBucketItem.observe ( viewLifecycleOwner , Observer{
            Log.d("newitem", this.toString() )
            bucketList.add(it)
            bucketAdapter.setItems(bucketList)})


        Log.d("bucketlist", bucketList.toString() )
        val addPosts : RecyclerView = view.findViewById(R.id.mylist)
        val layoutManager = LinearLayoutManager(context)
        addPosts.layoutManager = layoutManager
        addPosts.adapter = bucketAdapter
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
            BucketFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }}
