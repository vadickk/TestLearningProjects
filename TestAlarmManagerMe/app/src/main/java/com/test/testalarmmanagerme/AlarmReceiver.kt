package com.test.testalarmmanagerme

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.application.isradeleon.notify.Notify

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("MyLog", "ALARM RECEIVER")
        showNotificationWithLibrary(context!!, "ALARM!!", "WAKE UP BRO!!")
    }
    private fun showNotificationWithoutLibrary(
        context: Context,
        title: String,
        message: String
    ) {
        val notification: Notification
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = NotificationCompat.Builder(context, "Main Channel ID")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(message)
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
            notificationManager.notify((0..1_000_000).random(), notification)
        } else {
            notification = NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentText(message)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentTitle(title)
                .build()
            val notificationManager = context.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager
            notificationManager.notify((0..1_000_000).random(), notification)
        }
    }
    private fun showNotificationWithLibrary(context: Context, title: String, message: String) {
        Notify.build(context)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setTitle(title)
            .setImportance(Notify.NotifyImportance.HIGH)
            .setAutoCancel(true)
            .setChannelName("Notification With Library")
            .setContent(message)
            .show()
    }
}