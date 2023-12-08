package com.test.testmyserverktor.repostitory

import com.test.testmyserverktor.api.RetrofitInstance
import com.test.testmyserverktor.data.login.LoginReceiveRemote
import com.test.testmyserverktor.data.login.LoginResponseRemote
import com.test.testmyserverktor.data.register.RegisterReceiveRemote
import com.test.testmyserverktor.data.register.RegisterResponseRemote
import retrofit2.Response

class Repository {

    suspend fun register(registerResponseRemote: RegisterResponseRemote) : Response<RegisterReceiveRemote> {
        return RetrofitInstance.api.register(registerResponseRemote = registerResponseRemote)
    }

    suspend fun login(loginResponseRemote: LoginResponseRemote) : Response<LoginReceiveRemote> {
        return RetrofitInstance.api.login(loginResponseRemote = loginResponseRemote)
    }

}