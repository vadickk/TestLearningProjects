package com.main.testskeletonloading.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.main.testskeletonloading.databinding.ActivityMainBinding
import com.main.testskeletonloading.presentation.adapter.UsersAdapter
import com.main.testskeletonloading.presentation.viewmodel.MainViewModel
import com.main.testskeletonloading.presentation.viewmodel.MainViewModelFactory
import com.main.testskeletonloading.repository.RemoteDataSourceRepository

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var viewModel: MainViewModel
    private val usersAdapter = UsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()

        viewModel.users.observe(this) { response ->
            if (response.isSuccessful) {
                val users = response.body()?.toMutableList()
                users?.let { usersAdapter.setNewList(it) }
            }
        }
    }

    private fun init() {
        initViewModel()
    }

    private fun initViewModel() {
        val repository = RemoteDataSourceRepository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }
}