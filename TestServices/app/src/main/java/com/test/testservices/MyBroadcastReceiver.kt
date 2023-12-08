package com.test.testservices

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
            val bool = intent?.getBooleanExtra(Intent.ACTION_AIRPLANE_MODE_CHANGED, false) as Boolean
            if (bool) {
                Toast.makeText(context, "Airplane mode enabled", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Airplane mode disabled", Toast.LENGTH_SHORT).show()
            }
        }
    }

}