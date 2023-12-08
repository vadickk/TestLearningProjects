package com.test.testretrofit

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.test.testretrofit.databinding.ActivityMainBinding
import com.test.testretrofit.model.Post
import com.test.testretrofit.repository.Repository
import com.test.testretrofit.view_model.MainViewModel
import com.test.testretrofit.view_model.MainViewModelFactory


class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding.textView.movementMethod = ScrollingMovementMethod()
        pushPost()
    }
    private fun getPost1() {
        viewModel.getPost()
        viewModel.myResponse.observe(this) { response ->
            if (response.isSuccessful) {
                val data = response.body()
                Log.d("Repository", data?.userId.toString())
                Log.d("Repository", data?.id.toString())
                Log.d("Repository", data?.title.toString())
                Log.d("Repository", data?.body.toString())
            } else {
                Log.d("Repository", "Seems like something went wrong...")
            }
        }
    }
    private fun getPost2() {
        binding.btnGet.setOnClickListener {
            val number = binding.editTextNumber.text.toString().toInt()
            if (number in 1..100) {
                viewModel.getPost2(number)
            } else {
                binding.editTextNumber.error = "Please write number from 1 to 100"
            }
        }
        viewModel.myResponse2.observe(this) { response ->
            if (response.isSuccessful) {
                val data = response.body()
                binding.textView.text = data?.body
            } else {
                Toast.makeText(this, "Seems like something went wrong...", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun getPost3() {
        binding.editTextNumber.hint = "A number from 1 to 10"
        binding.btnGet.setOnClickListener {
            val userId = binding.editTextNumber.text.toString().toInt()
            if (userId in 1..10) {
                viewModel.getPost3(userId)
            } else {
                binding.editTextNumber.error = "Please write number from 1 to 10"
            }
        }
        viewModel.myResponse3.observe(this) { response ->
            if (response.isSuccessful) {
                val data = response.body()
                data?.forEach {
                    binding.textView.append("${it.body}\n\n")
                }
            } else {
                Toast.makeText(this, "Seems like something went wrong...", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun pushPost() {
        val myPost = Post(11, 101, "Ukraine", "Slava Ukraini")
        viewModel.pushPost(myPost)
        viewModel.myResponsePush.observe(this) { response ->
            if (response.isSuccessful) {
                Log.d("MyLog", response.body().toString())
                Log.d("MyLog", response.code().toString())
                Log.d("MyLog", response.message())
            } else {
                Toast.makeText(this, "Seems like something went wrong...", Toast.LENGTH_LONG).show()
            }
        }
    }
}