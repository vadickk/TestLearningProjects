package com.test.testmyserverktor.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.testmyserverktor.databinding.ActivityMainBinding
import com.test.testmyserverktor.utils.Constants.REGISTER_ACTIVITY
import com.test.testmyserverktor.utils.Constants.REGISTER_LOGIN_KEY

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val activity = intent.getStringExtra(REGISTER_LOGIN_KEY)
        if (activity == REGISTER_ACTIVITY) {
            binding.btnLogOut.setOnClickListener {
                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            }
        } else {
            binding.btnLogOut.setOnClickListener {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}