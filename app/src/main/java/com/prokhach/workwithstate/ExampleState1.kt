package com.prokhach.workwithstate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.prokhach.workwithstate.databinding.ActivityMainBinding
import kotlin.random.Random

class ExampleState1 : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        initViews()
    }

    private fun increment() {
        var counter = binding.counterTextView.text.toString().toInt()
        counter++
        binding.counterTextView.text = counter.toString()
    }

    private fun setRandomColor() {
        val randomColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        binding.counterTextView.setTextColor(randomColor)
    }

    private fun switchVisibility() = with(binding.counterTextView) {
        visibility = if (visibility == VISIBLE)
            INVISIBLE
        else
            VISIBLE
    }

    private fun initViews() = with(binding) {
        incrementButton.setOnClickListener { increment() }
        randomColorButton.setOnClickListener { setRandomColor() }
        switchVisibilityButton.setOnClickListener { switchVisibility() }
    }
}
