package com.test.testnotifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.application.isradeleon.notify.Notify
import com.test.testnotifications.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSendNotification.setOnClickListener {
//            showNotificationWithoutLibrary(
//                context = this,
//                title = "Hello World",
//                message = "Hi-hi u are craze"
//            )
            showNotificationWithLibrary()
        }
    }

    private fun showNotificationWithoutLibrary(
        context: Context,
        title: String,
        message: String
    ) {
        val notification: Notification
        val intent = Intent(this, MainActivity2::class.java)
        val pendingIntent: PendingIntent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            pendingIntent = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_IMMUTABLE)
            notification = NotificationCompat.Builder(context, "Main Channel ID")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .build()
            val notificationManager = context.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager
            val notificationChannel = NotificationChannel(
                "Main Channel ID",
                "Notification Without Library",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(notificationChannel)
            notificationManager.notify(100, notification)
        } else {
            pendingIntent = PendingIntent.getActivity(this, 100, intent, 0)
            notification = NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentText(message)
                .setContentIntent(pendingIntent)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentTitle(title)
                .build()
            val notificationManager = context.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager
            notificationManager.notify(100, notification)
        }
    }

    private fun showNotificationWithLibrary() {
        Notify.build(this)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setTitle("HelloWorld")
            .setColor(R.color.teal_200)
            .setId(1)
            .setImportance(Notify.NotifyImportance.LOW)
            .setPicture(R.drawable.ic_launcher_foreground)
            .setAction(Intent(this, MainActivity2::class.java))
            .setAutoCancel(true)
            .setChannelName("Notification With Library")
            .setContent("I'm vadym, and fuck you baby")
            .show()
    }
}