package com.main.testcustomview

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.main.testcustomview.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        CoroutineScope(Dispatchers.IO).launch {
//            repeatDisco()
//        }
        binding.customTestView.setOnClickListener {
            val bitmap = Bitmap.createBitmap(
                100,
                100,
                Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            binding.customTestView.draw(canvas)
        }
    }

    private suspend fun repeatDisco() {
        while (true) {
            delay(300)
            withContext(Dispatchers.Main) {
                binding.customTestView.changeColor()
            }
        }
    }
}