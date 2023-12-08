package com.test.testretrofit.api

import com.test.testretrofit.model.Post
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface SimpleApi {

    @GET("/posts/1")
    suspend fun getPost() : Response<Post>

    @GET("/posts/{postNumber}")
    suspend fun getPost2(
        @Path("postNumber") number: Int
    ) : Response<Post>

    @GET("/posts")
    suspend fun getPost3(
        @Query("userId") userId: Int
    ) : Response<List<Post>>

    @POST("/posts")
    suspend fun pushPost1(
        @Body post: Post
    ) : Response<Post>
}