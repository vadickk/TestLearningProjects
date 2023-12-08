package com.main.testskeletonloading.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.main.testskeletonloading.repository.RemoteDataSourceRepository

class MainViewModelFactory(
    val remoteDataSourceRepository: RemoteDataSourceRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(remoteDataSourceRepository) as T
    }
}