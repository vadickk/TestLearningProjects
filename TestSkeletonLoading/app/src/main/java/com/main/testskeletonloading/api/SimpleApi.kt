package com.main.testskeletonloading.api

import com.main.testskeletonloading.model.User
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("/users")
    suspend fun getUsers() : Response<List<User>>

}