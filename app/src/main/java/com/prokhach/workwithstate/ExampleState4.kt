package com.prokhach.workwithstate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prokhach.workwithstate.databinding.ActivityMainBinding

class ExampleState4 : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

//        initViews()
    }
}
