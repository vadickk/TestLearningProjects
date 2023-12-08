package com.test.testviewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.test.testviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataModel: ClassViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setFragment(FirstFragment.newInstance(), R.id.frameLayoutFragment1)
        setFragment(SecondFragment.newInstance(), R.id.frameLayoutFragment2)

        dataModel.getMessageActivity().observe(this) {
            binding.textViewActivity.text = it
        }

    }
    private fun setFragment(fragment: Fragment, placeHolder: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(placeHolder, fragment)
            .commit()
    }

}