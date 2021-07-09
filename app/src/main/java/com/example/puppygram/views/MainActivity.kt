package com.example.puppygram.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.puppygram.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigateToPuppyFragment()
    }

    private fun navigateToPuppyFragment(){
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.main_frame_layout, PuppyGramFragment())
        ft.commit()
    }
}