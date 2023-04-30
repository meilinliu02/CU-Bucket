package com.example.hackchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_item -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, FeedFragment.newInstance())
                }
                R.id.add_item -> {

                }
                R.id.bucket_item -> {

                }
            }
            true
        }


    }
}