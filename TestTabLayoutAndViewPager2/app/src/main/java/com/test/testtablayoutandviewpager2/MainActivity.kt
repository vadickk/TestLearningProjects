package com.test.testtablayoutandviewpager2

import android.app.AppComponentFactory
import android.os.Bundle
import info.fandroid.viewpager2app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ViewPagerAdapter
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    private val arrayOfFigures = arrayOf("1", "2", "3", "4", "5", "6", "7")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabLayout = binding.tabLayout
        viewPager2 = binding.viewPager
        adapter = ViewPagerAdapter(this)
        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, pos ->
            tab.text = arrayOfFigures[pos]
        }.attach()
    }


}