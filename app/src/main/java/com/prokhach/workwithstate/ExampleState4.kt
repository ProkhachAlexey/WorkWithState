package com.prokhach.workwithstate

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.prokhach.workwithstate.databinding.ActivityMainBinding
import java.util.Observer

class ExampleState4 : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val viewModel: ExampleState4ViewModel by viewModels<ExampleState4ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        initViews()

        viewModel.initState(
            savedInstanceState?.getParcelable(KEY_STATE) ?: ExampleState4ViewModel.State(
                counterValue = 0,
                counterTextColor = ContextCompat.getColor(this, R.color.purple_700),
                counterIsVisible = true
            )
        )

        viewModel.state.observe(this, androidx.lifecycle.Observer {
            renderState(it)
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_STATE, viewModel.state.value)
    }

    private fun initViews() = with(binding) {
        incrementButton.setOnClickListener { viewModel.increment() }
        randomColorButton.setOnClickListener { viewModel.setRandomColor() }
        switchVisibilityButton.setOnClickListener { viewModel.switchVisibility() }
    }

    private fun renderState(state: ExampleState4ViewModel.State) = with(binding.counterTextView) {
        text = state.counterValue.toString()
        setTextColor(state.counterTextColor)
        visibility = if (state.counterIsVisible) View.VISIBLE else View.INVISIBLE
    }

    private companion object {

        const val KEY_STATE = "STATE"
    }
}
