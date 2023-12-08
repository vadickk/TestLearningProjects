package com.test.testcoroutines

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.test.testcoroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private val TAG = "TestCoroutinesTest"
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    @OptIn(ExperimentalCoroutinesApi::class)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val job = doAsyncJob()
                var text1: String? = null
                var text2: String? = null
                Log.d(TAG, "Request starting..")
                val request = async { text1 = doNetworkRequest1() }
                val request2 = async { text2 = doNetworkRequest2() }
                Log.d(TAG, "Request ending..")
                request.await()
                request2.await()
                withContext(Dispatchers.Main) {
                    Log.d(TAG, "BINGING...")
                    binding.textView.text = "$text1 | ${job.isCompleted}"
                    binding.textView2.text = "$text2 | ${job.isCompleted}"
                }
            }
        }
    }

    private suspend fun doNetworkRequest1() : String {
        val time = (1000..3000).random().toLong()
        delay(time)
        return time.toString()
    }
    private suspend fun doNetworkRequest2() : String {
        val time = (1000..3000).random().toLong()
        delay(time)
        return time.toString()
    }
}