package com.test.testservices

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyForegroundService : Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Thread {
            repeat(5) {
                Log.d("MyLog", "Service running..")
                Thread.sleep(2000)
            }
        }.start()
        val idText = "Foreground Service ID"
        val notificationChannel = NotificationChannel(
            idText,
            idText,
            NotificationManager.IMPORTANCE_LOW
        )
        getSystemService(NotificationManager::class.java).createNotificationChannel(notificationChannel)
        val notification = Notification.Builder(this, idText)
            .setContentText("Service is running")
            .setContentTitle("Service enabled")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
        startForeground(1, notification.build())
        return super.onStartCommand(intent, flags, startId)
    }

}