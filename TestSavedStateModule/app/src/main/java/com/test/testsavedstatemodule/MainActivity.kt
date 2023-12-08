package com.test.testsavedstatemodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.test.testsavedstatemodule.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val text = binding.etText.text.toString()
            mainViewModel.changeText(text = text)
        }

        mainViewModel.liveData.observe(this) {
            binding.tvText.text = it
        }
    }
}