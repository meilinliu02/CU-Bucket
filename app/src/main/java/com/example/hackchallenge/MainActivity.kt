package com.example.hackchallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.internal.wait

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        run()
        Thread.sleep(3000) // Wait for response to hopefully come back. execute() was not working.

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, FeedFragment.newInstance())
            .commit()
        supportFragmentManager.beginTransaction().commit()
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home_item -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, FeedFragment.newInstance()).commit()
                }
                R.id.add_item -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, AddFragment()).commit()
                }
                R.id.bucket_item -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, BucketFragment()).commit()
                }
            }
            true
        }


    }
}