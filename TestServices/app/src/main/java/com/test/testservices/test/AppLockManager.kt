package com.test.testservices.test

import android.content.Context
import android.content.SharedPreferences

class AppLockManager(private val context: Context) {
    private val PREF_NAME = "AppLock"
    private val KEY_PASSWORD = "password"
    private val KEY_LOCKED_APPS = "locked_apps"

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setPassword(password: String) {
        sharedPreferences.edit().putString(KEY_PASSWORD, password).apply()
    }

    fun checkPassword(password: String): Boolean {
        val savedPassword = sharedPreferences.getString(KEY_PASSWORD, null)
        return password == savedPassword
    }

    fun lockApp(packageName: String) {
        val lockedApps = getLockedApps().toMutableSet()
        lockedApps.add(packageName)
        sharedPreferences.edit().putStringSet(KEY_LOCKED_APPS, lockedApps).apply()
    }

    fun unlockApp(packageName: String) {
        val lockedApps = getLockedApps().toMutableSet()
        lockedApps.remove(packageName)
        sharedPreferences.edit().putStringSet(KEY_LOCKED_APPS, lockedApps).apply()
    }

    fun isAppLocked(packageName: String): Boolean {
        val lockedApps = getLockedApps()
        return lockedApps.contains(packageName)
    }

    private fun getLockedApps(): Set<String> {
        return sharedPreferences.getStringSet(KEY_LOCKED_APPS, emptySet()) ?: emptySet()
    }
}
