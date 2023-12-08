package com.test.testacitivtynavigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.test.testacitivtynavigation.databinding.ActivityMainBinding
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    private var binding by Delegates.notNull<ActivityMainBinding>()
    private var launcher by Delegates.notNull<ActivityResultLauncher<Intent>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            Log.d("MyLog", "launcher is launch")
            if (result.resultCode == RESULT_OK) {
                binding.textToGetDataWithSecondActiviity.text = result.data?.getStringExtra("Text")
            }
        }

        binding.buttonToStartSecondActivity.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            launcher.launch(intent)
        }
    }

}