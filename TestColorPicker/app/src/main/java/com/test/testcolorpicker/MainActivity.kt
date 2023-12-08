package com.test.testcolorpicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.testcolorpicker.databinding.ActivityMainBinding
import kotlin.math.absoluteValue

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        R.attr.colorTextPrimary
    }
}