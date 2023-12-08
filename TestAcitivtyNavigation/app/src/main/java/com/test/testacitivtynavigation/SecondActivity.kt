package com.test.testacitivtynavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.testacitivtynavigation.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonToStartMainActivity.setOnClickListener {
            val intent = Intent()
            intent.putExtra("Text", binding.editTextTextPersonName.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }

}