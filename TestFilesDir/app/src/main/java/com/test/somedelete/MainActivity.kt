package com.test.somedelete

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.test.somedelete.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val FILENAME = "MyFile.txt"
        binding.button.setOnClickListener {
            val fos: FileOutputStream = openFileOutput(FILENAME, Context.MODE_APPEND)
            var string = "Some ${(0..10000).random()} \n"
            fos.write(string.toByteArray())

            val br = BufferedReader(InputStreamReader(openFileInput("MyFile.txt")))
            var string1: String? = ""
            var buf: String?
            while (br.readLine().also { buf = it } != null) {
                string1 += buf
            }
            val result = string1.toString()
            Log.d("MyLog", result)
        }
    }

}