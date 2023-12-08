package com.test.testretrofit.repository

import com.test.testretrofit.api.RetrofitInstance
import com.test.testretrofit.api.SimpleApi
import com.test.testretrofit.model.Post
import retrofit2.Call
import retrofit2.Response

class Repository {

    suspend fun getPost() : Response<Post> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPost2(number: Int) : Response<Post> {
        return RetrofitInstance.api.getPost2(number)
    }

    suspend fun getPost3(userId: Int) : Response<List<Post>> {
        return RetrofitInstance.api.getPost3(userId)
    }

    suspend fun pushPost(post: Post) : Response<Post> {
        return RetrofitInstance.api.pushPost1(post)
    }

}