package com.test.preferencesdatastore

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.test.preferencesdatastore.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val Context.dataStore by preferencesDataStore("settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSaveData.setOnClickListener {
            lifecycleScope.launch {
                val key = binding.etKey.text.toString()
                val value = binding.etValue.text.toString()
                saveData(key = key, value = value)
                binding.etKey.text?.clear()
                binding.etValue.text?.clear()
            }
        }

        binding.btnGetData.setOnClickListener {
            lifecycleScope.launch {
                val key = binding.etWriteKeyAndGetValue.text.toString()
                val value = getData(key = key)
                binding.tvDataResult.text = value
            }
        }
    }
    //For save our data
    private suspend fun saveData(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }
    //For get our data
    private suspend fun getData(key: String): String? {
        val dataStoreKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }
    //For update our data
    private suspend fun updateData(key: String, value: String) {
        val dataStoreKey = stringPreferencesKey(key)
        dataStore.updateData { preferences ->
            val snapshot = preferences.toMutablePreferences()
            snapshot[dataStoreKey] = value
            return@updateData snapshot
        }
    }

}