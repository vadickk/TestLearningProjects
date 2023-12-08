package com.test.testmyserverktor.data.register

data class RegisterResponseRemote(
    val login: String,
    val password: String,
    val email: String
)

data class RegisterReceiveRemote(
    val token: String
)