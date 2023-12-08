package com.test.testanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.test.testanimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), Animation.AnimationListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appear: Animation
    private lateinit var disappear: Animation
    private val array = arrayOf(R.drawable.some1, R.drawable.some2png)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appear = AnimationUtils.loadAnimation(this, R.anim.appear)
        disappear = AnimationUtils.loadAnimation(this, R.anim.disappear)
        disappear.setAnimationListener(this)

        binding.imageView.setOnClickListener {
            binding.imageView.startAnimation(disappear)
        }
    }
    //These two functions will be without animation
    override fun onAnimationStart(animation: Animation?) {}
    override fun onAnimationRepeat(animation: Animation?) {}
    //This function will have an animation
    override fun onAnimationEnd(animation: Animation?) {
        binding.imageView.startAnimation(appear)
        binding.imageView.setImageResource(array.random())
    }
}