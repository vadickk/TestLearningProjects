package com.test.testservices

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.testservices.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnStartService.setOnClickListener {
            if (!isServiceRunning(MyBackgroundService::class.java)) {
                Intent(this@MainActivity, MyBackgroundService::class.java).also {
                    startService(it)
                    binding.tvServiceInfo.text = "Service running..."
                }
            }
        }
        binding.btnSentData.setOnClickListener {
            if (!isServiceRunning(MyBackgroundService::class.java)) {
                Intent(this@MainActivity, MyBackgroundService::class.java).also {
                    val dataString = binding.etData.text.toString()
                    it.putExtra("EXTRA_DATA", dataString)
                    startService(it)
                }
            }
        }
        binding.btnStopService.setOnClickListener {
            Intent(this@MainActivity, MyBackgroundService::class.java).also {
                stopService(it)
            }
        }
        binding.startForegroundService.setOnClickListener {
            if (!bool()) {
                Intent(this@MainActivity, MyForegroundService::class.java).also {
                    startForegroundService(it)
                }
            }
        }
        binding.stopForegroundService.setOnClickListener {
            Intent(this@MainActivity, MyForegroundService::class.java).also {
                stopService(it)
            }
        }
    }
    private fun bool() : Boolean{
        val activityManager: ActivityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service: ActivityManager.RunningServiceInfo in activityManager.getRunningServices(Int.MAX_VALUE)) {
            if (MyForegroundService::class.java.name.equals(service.service.className)){
                return true
            }
        }
        return false
    }


    private fun isServiceRunning(serviceClass: Class<*>): Boolean {
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        // Loop through the running services
        for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                // If the service is running then return true
                return true
            }
        }
        return false
    }

}