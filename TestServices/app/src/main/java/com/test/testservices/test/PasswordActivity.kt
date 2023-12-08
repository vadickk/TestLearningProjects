package com.test.testservices.test

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.test.testservices.R

class PasswordActivity : AppCompatActivity() {

    private lateinit var appLockManager: AppLockManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        appLockManager = AppLockManager(this)

        val packageName = intent.getStringExtra("packageName").toString()

        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val unlockButton: Button = findViewById(R.id.unlockButton)
        unlockButton.setOnClickListener {
            val password = passwordEditText.text.toString()
            if (appLockManager.checkPassword(packageName)) {
                launchApp(packageName)
                finish()
            } else {
                // Display an error message or dialog for incorrect password
            }
        }
    }

    private fun launchApp(packageName: String) {
        val launchIntent = packageManager.getLaunchIntentForPackage(packageName)
        startActivity(launchIntent)
    }
}
