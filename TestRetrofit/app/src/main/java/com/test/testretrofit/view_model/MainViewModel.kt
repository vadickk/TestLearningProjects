package com.test.testretrofit.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.testretrofit.model.Post
import com.test.testretrofit.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse by lazy { MutableLiveData<Response<Post>>() }
    val myResponse2 by lazy { MutableLiveData<Response<Post>>() }
    val myResponse3 by lazy { MutableLiveData<Response<List<Post>>>() }
    val myResponsePush by lazy { MutableLiveData<Response<Post>>() }

    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response
        }
    }
    fun getPost2(number: Int) {
        viewModelScope.launch {
            val response = repository.getPost2(number)
            myResponse2.value = response
        }
    }
    fun getPost3(userId: Int) {
        viewModelScope.launch {
            val response = repository.getPost3(userId)
            myResponse3.value = response
        }
    }

    fun pushPost(post: Post) {
        viewModelScope.launch {
            val response = repository.pushPost(post)
            myResponsePush.value = response
        }
    }
}