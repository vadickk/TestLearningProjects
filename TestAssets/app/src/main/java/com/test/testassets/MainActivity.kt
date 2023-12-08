package com.test.testassets

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.IOUtils
import com.google.gson.Gson
import com.test.testassets.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.reflect.Type
import java.nio.charset.StandardCharsets

typealias ArrayOfPerson = Array<Person>

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnGetData.setOnClickListener {
            readJson()
        }

    }

    private fun readJson() {
        val gson = Gson()
        val inputStream = applicationContext.assets.open("First.json")
        val text = IOUtils.toString(inputStream, StandardCharsets.UTF_8)
        val result = gson.fromJson(text, ArrayOfPerson::class.java)
        for (person in result) {
            Log.d("MyLog", person.toString())
        }
//        val result = jsonParser(text)
    }

//    private fun jsonParser(text: String): List<Person> {
//        val parser = Gson()
//        val result = mutableListOf<Person>()
//
//        return parser.fromJson(text, Array<Person>)
//    }
}