package com.test.testservices.test

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder

class AppLockService : Service() {
    private lateinit var appLockManager: AppLockManager
    private lateinit var appLaunchReceiver: BroadcastReceiver

    override fun onCreate() {
        super.onCreate()
        appLockManager = AppLockManager(this)
        appLaunchReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val packageName = intent.data?.schemeSpecificPart
                if (packageName != null && appLockManager.isAppLocked(packageName)) {
                    // Відображення екрану вводу пароля
                    val passwordIntent = Intent(context, PasswordActivity::class.java)
                    passwordIntent.putExtra("packageName", packageName)
                    passwordIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(passwordIntent)
                }
            }
        }
        val filter = IntentFilter(Intent.ACTION_PACKAGE_ADDED)
        filter.addDataScheme("package")
        registerReceiver(appLaunchReceiver, filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(appLaunchReceiver)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}
