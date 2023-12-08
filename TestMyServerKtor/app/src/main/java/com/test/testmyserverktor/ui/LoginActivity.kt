package com.test.testmyserverktor.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.testmyserverktor.databinding.ActivityLoginBinding
import com.test.testmyserverktor.utils.Constants.LOGIN_ACTIVITY
import com.test.testmyserverktor.utils.Constants.REGISTER_LOGIN_KEY
import com.test.testmyserverktor.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        goToRegister()
        login()
    }

    private fun goToRegister() {
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }
    }
    private fun login() {
        binding.btnLogin.setOnClickListener {
            val result = mainViewModel.checkValidDataAndLogin(
                login = binding.etLogin.text.toString(),
                password = binding.etPassword.text.toString(),
                binding
            )
            if (result) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(REGISTER_LOGIN_KEY, LOGIN_ACTIVITY)
                startActivity(intent)
                finish()
            }
        }
    }
}