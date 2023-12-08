package com.test.testmyserverktor.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.testmyserverktor.data.login.LoginReceiveRemote
import com.test.testmyserverktor.data.login.LoginResponseRemote
import com.test.testmyserverktor.data.register.RegisterReceiveRemote
import com.test.testmyserverktor.data.register.RegisterResponseRemote
import com.test.testmyserverktor.data.valid.ValidData
import com.test.testmyserverktor.databinding.ActivityLoginBinding
import com.test.testmyserverktor.databinding.ActivityRegisterBinding
import com.test.testmyserverktor.repostitory.Repository
import kotlinx.coroutines.launch

class MainViewModel(
    val repository: Repository
) : ViewModel() {

    private suspend fun register(registerResponseRemote: RegisterResponseRemote) : RegisterReceiveRemote? {
        var result: RegisterReceiveRemote? = null
        val response = repository.register(registerResponseRemote)
        if (response.isSuccessful) {
            result = response.body()
        }
        return result
    }

    private suspend fun login(loginResponseRemote: LoginResponseRemote) : LoginReceiveRemote? {
        var result: LoginReceiveRemote? = null
        val response = repository.login(loginResponseRemote = loginResponseRemote)
        if (response.isSuccessful) {
            result = response.body()
        }
        return result
    }

    private fun checkValidLogin(login: String) : ValidData {
        if (login.isEmpty()) return ValidData(boolean = false, message = "Login is empty")
        if (login.length < 3) return ValidData(boolean = false, message = "Login is too short")
        if (login.length > 25) return ValidData(boolean = false, message = "Login is too long")
        return ValidData(boolean = true)
    }

    private fun checkValidPassword(password: String) : ValidData {
        if (password.isEmpty()) return ValidData(boolean = false, message = "Password is empty")
        if (password.length < 6) return ValidData(boolean = false, message = "Password is too short")
        if (password.length > 25) return ValidData(boolean = false, message = "Password is too long")
        return ValidData(boolean = true)
    }

    private fun checkValidEmail(email: String) : ValidData {
        if (email.isEmpty()) return ValidData(boolean = false, message = "Email is empty")
        if (email.length < 6) return ValidData(boolean = false, message = "Email is too short")
        if (email.length > 35) return ValidData(boolean = false, message = "Email is too long")
        return ValidData(boolean = true)
    }

    fun checkValidDataAndRegister(login: String, password: String, email: String, binding: ActivityRegisterBinding) : Boolean {
        var result = false
        val resultLogin = checkValidLogin(login)
        val resultPassword = checkValidPassword(password)
        val resultEmail = checkValidEmail(email)
        val registerResponseRemote = RegisterResponseRemote(login = login, password = password, email = email)
        when(false) {
            resultEmail.boolean -> binding.etEmail.error = resultEmail.message
            resultLogin.boolean -> binding.etLogin.error = resultLogin.message
            resultPassword.boolean -> binding.etPassword.error = resultPassword.message
            else -> viewModelScope.launch {
                result = true
                register(registerResponseRemote = registerResponseRemote)
            }
        }
        return result
    }

    fun checkValidDataAndLogin(login: String, password: String, binding: ActivityLoginBinding) : Boolean {
        var result = false
        val resultLogin = checkValidLogin(login)
        val resultPassword = checkValidPassword(password)
        val loginResponseRemote = LoginResponseRemote(login = login, password = password)
        when(false) {
            resultLogin.boolean -> binding.etLogin.error = resultLogin.message
            resultPassword.boolean -> binding.etPassword.error = resultPassword.message
            else -> viewModelScope.launch {
                val result1 = login(loginResponseRemote = loginResponseRemote)
                if (result1 != null) {
                    result = true
                }
            }
        }
        return result
    }

}