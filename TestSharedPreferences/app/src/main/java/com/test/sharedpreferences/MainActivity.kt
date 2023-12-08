package com.test.sharedpreferences

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.test.sharedpreferences.databinding.ActivityMainBinding

const val APP_PREFERENCES = "APP_PREFERENCES"
const val PREF_KEY = "KEY"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var preferences: SharedPreferences

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { preferences, key ->
        if (key == PREF_KEY) {
            binding.textView.text = preferences.getString(PREF_KEY, "")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE)

        binding.editTextTextPersonName.setText(preferences.getString(PREF_KEY, ""))
        binding.textView.text = preferences.getString(PREF_KEY, "")
        binding.button.setOnClickListener {
            val value = binding.editTextTextPersonName.text.toString()
            preferences.edit().putString(PREF_KEY, value).apply()
        }
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun onDestroy() {
        super.onDestroy()
        preferences.unregisterOnSharedPreferenceChangeListener(listener)
    }
}