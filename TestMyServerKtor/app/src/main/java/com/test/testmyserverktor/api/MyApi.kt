package com.test.testmyserverktor.api

import com.test.testmyserverktor.data.login.LoginReceiveRemote
import com.test.testmyserverktor.data.login.LoginResponseRemote
import com.test.testmyserverktor.data.register.RegisterReceiveRemote
import com.test.testmyserverktor.data.register.RegisterResponseRemote
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface MyApi {

    @POST("/login")
    suspend fun login(@Body loginResponseRemote: LoginResponseRemote) : Response<LoginReceiveRemote>

    @POST("/register")
    suspend fun register(@Body registerResponseRemote: RegisterResponseRemote) : Response<RegisterReceiveRemote>

}