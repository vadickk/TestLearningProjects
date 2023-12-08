package com.test.testflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.test.testflow.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //On Click
        binding.buttonFlow.setOnClickListener {
            lifecycleScope.launch {
                viewModel.changeFlow().collectLatest {
                    binding.textFlow.text = it
                }
            }
        }
        binding.buttonStateFlow.setOnClickListener {
            viewModel.changeStateFlow()
        }
        binding.buttonSharedFlow.setOnClickListener {
            viewModel.changeSharedFlow()
        }
        binding.buttonLiveData.setOnClickListener {
            viewModel.changeLiveData()
        }
        binding.buttonChannel.setOnClickListener {
            viewModel.changeChannel()
        }
        //Get Data
        lifecycleScope.launch {
            viewModel.sharedFlow.collectLatest {
                binding.textSharedFlow.text = it
            }
        }
        lifecycleScope.launch {
            viewModel.stateFlow.collect {
                binding.textStateFlow.text = it
            }
        }
        lifecycleScope.launch {
            viewModel.channel.collect {
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_LONG
                ).show()
                binding.textChannel.text = it
            }
        }
        viewModel.liveData.observe(this) {
            binding.textLiveData.text = it
        }
    }
}