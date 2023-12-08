package com.test.testchannels

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.test.testchannels.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonShowSnackbar.setOnClickListener {
            viewModel.triggerEvent()
        }
        lifecycleScope.launch {
            viewModel.eventFlow.collect { myEvent ->
                when(myEvent) {
                    is MainViewModel.MyEvent.ErrorEvent -> {
                        Snackbar.make(binding.root, myEvent.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}