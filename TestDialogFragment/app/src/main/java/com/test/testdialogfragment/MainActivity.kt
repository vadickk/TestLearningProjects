package com.test.testdialogfragment

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentResultListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        simpleDialog()
        setupSimpleDialogFragmentListener()

    }
    private fun simpleDialog() {
        val simpleDialog = SimpleFragmentDialog()
        simpleDialog.show(supportFragmentManager, SimpleFragmentDialog.TAG)
    }
    private fun setupSimpleDialogFragmentListener() {
        supportFragmentManager.setFragmentResultListener(SimpleFragmentDialog.REQUEST_KEY, this, FragmentResultListener { _, result ->
            when (result.getInt(SimpleFragmentDialog.KEY_RESPONSE)) {
                DialogInterface.BUTTON_POSITIVE -> Toast.makeText(this, "Positive", Toast.LENGTH_LONG).show()
                DialogInterface.BUTTON_NEGATIVE -> Toast.makeText(this, "Negative", Toast.LENGTH_LONG).show()
                DialogInterface.BUTTON_NEUTRAL -> Toast.makeText(this, "Ignore", Toast.LENGTH_LONG).show()
            }
        })
    }
}