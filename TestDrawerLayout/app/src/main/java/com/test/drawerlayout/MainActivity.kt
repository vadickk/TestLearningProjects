package com.test.drawerlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.test.drawerlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.openMenuButton.setOnClickListener {
            binding.drawer.open()
        }
        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.item11 -> Log.d("MyLog", "Item 11")
                R.id.item12 -> Toast(this@MainActivity).showToastForMainActivity("Item 12")
                R.id.item13 -> Toast(this@MainActivity).showToastForMainActivity("Item 13")
                R.id.item21 -> Toast(this@MainActivity).showToastForMainActivity("Item 21")
                R.id.item22 -> Toast(this@MainActivity).showToastForMainActivity("Item 22")
                R.id.item23 -> Toast(this@MainActivity).showToastForMainActivity("Item 23")
            }
            binding.drawer.close()
            true
        }
    }

    private fun Toast.showToastForMainActivity(text: String) {
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
    }

}