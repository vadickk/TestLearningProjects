package com.test.testmyserverktor.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.testmyserverktor.databinding.ActivityRegisterBinding
import com.test.testmyserverktor.utils.Constants
import com.test.testmyserverktor.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        goToLogin()
        register()
    }

    private fun goToLogin() {
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun register() {
        binding.btnRegister.setOnClickListener {
            val result = mainViewModel.checkValidDataAndRegister(
                login = binding.etLogin.text.toString(),
                password = binding.etPassword.text.toString(),
                email = binding.etEmail.text.toString(),
                binding
            )
            if (result) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra(Constants.REGISTER_LOGIN_KEY, Constants.REGISTER_ACTIVITY)
                startActivity(intent)
                finish()
            }
        }
    }
}