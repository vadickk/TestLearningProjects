package com.test.testcachedir

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.testcachedir.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val filename = "myfile"
        binding.button.setOnClickListener {
            val fileContents = "Hello world! ${(0..10000).random()}\n"
            applicationContext.openFileOutput(filename, Context.MODE_APPEND).use {
                it.write(fileContents.toByteArray())
            }
            val br = BufferedReader(InputStreamReader(openFileInput(filename)))
            var string1: String? = ""
            var buf: String?
            while (br.readLine().also { buf = it } != null) {
                string1 += "${buf}\n"
            }
            val result = string1.toString()
            Log.d("MyLog", result)
        }
    }
}