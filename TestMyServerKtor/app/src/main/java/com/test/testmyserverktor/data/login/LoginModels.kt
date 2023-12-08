package com.test.testmyserverktor.data.login

data class LoginResponseRemote(
    val login: String,
    val password: String
)

data class LoginReceiveRemote(
    val token: String
)