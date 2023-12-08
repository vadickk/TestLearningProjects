package com.main.testskeletonloading.repository

import com.main.testskeletonloading.api.RetrofitInstance
import com.main.testskeletonloading.model.User
import retrofit2.Response

class RemoteDataSourceRepository {

    suspend fun getUsers() : Response<List<User>> {
        return RetrofitInstance.api.getUsers()
    }

}