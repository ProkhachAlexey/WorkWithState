package com.prokhach.workwithstate

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.prokhach.workwithstate.databinding.ActivityMainBinding
import kotlin.properties.Delegates.notNull
import kotlin.random.Random

class ExampleState2 : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var counterValue by notNull<Int>()
    private var counterTextColor by notNull<Int>()
    private var counterIsVisible by notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        initViews()

        if (savedInstanceState == null) {
            counterValue = 0
            counterTextColor = ContextCompat.getColor(this, R.color.purple_700)
            counterIsVisible = true
        } else {
            counterValue = savedInstanceState.getInt(KEY_COUNTER)
            counterTextColor = savedInstanceState.getInt(KEY_COLOR)
            counterIsVisible = savedInstanceState.getBoolean(KEY_IS_VISIBLE)
        }
        renderState()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNTER, counterValue)
        outState.putInt(KEY_COLOR, counterTextColor)
        outState.putBoolean(KEY_IS_VISIBLE, counterIsVisible)
    }

    private fun increment() {
        counterValue++
        renderState()
    }

    private fun setRandomColor() {
        counterTextColor = Color.rgb(
            Random.nextInt(256),
            Random.nextInt(256),
            Random.nextInt(256)
        )
        renderState()
    }

    private fun switchVisibility() {
        counterIsVisible = !counterIsVisible
        renderState()
    }

    private fun initViews() = with(binding) {
        incrementButton.setOnClickListener { increment() }
        randomColorButton.setOnClickListener { setRandomColor() }
        switchVisibilityButton.setOnClickListener { switchVisibility() }
    }

    private fun renderState() = with(binding.counterTextView) {
        text = counterValue.toString()
        setTextColor(counterTextColor)
        visibility = if (counterIsVisible) View.VISIBLE else View.INVISIBLE
    }

    private companion object {

        const val KEY_COUNTER = "COUNTER"
        const val KEY_COLOR = "COLOR"
        const val KEY_IS_VISIBLE = "IS_VISIBLE"
    }
}
