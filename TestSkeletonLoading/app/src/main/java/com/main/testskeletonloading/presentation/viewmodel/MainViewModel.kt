package com.main.testskeletonloading.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.main.testskeletonloading.model.User
import com.main.testskeletonloading.repository.RemoteDataSourceRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(
    val remoteDataSourceRepository: RemoteDataSourceRepository
): ViewModel() {

    val users by lazy { MutableLiveData<Response<List<User>>>() }

    init {
        viewModelScope.launch {
            getUsers()
        }
    }

    suspend fun getUsers() {
        viewModelScope.launch {
            users.value = remoteDataSourceRepository.getUsers()
        }
    }

}