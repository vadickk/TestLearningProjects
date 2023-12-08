package com.test.testflowwithroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.test.testflowwithroom.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.btnGetData.setOnClickListener {
            lifecycleScope.launch {
                //first decision
//                viewModel.getAllToDo().collect { listOfToDoModel ->
//                    listOfToDoModel.forEach { toDoModel ->
//                        Log.d("MyLog", toDoModel.toString())
//                    }
//                }
                //second decision
                viewModel.getAllToDo()
            }
        }
        binding.btnSaveToDb.setOnClickListener {
            lifecycleScope.launch {
                val toDoModel = ToDoModel(
                    title = binding.etTitle.text.toString()
                )
                viewModel.insert(toDoModel = toDoModel)
                Log.d("MyLog", "Insert")
            }
        }
        //second decision
        lifecycleScope.launch {
            viewModel.some.collect { listOfToDoModel ->
                listOfToDoModel.forEach { toDoModel ->
                    Log.d("MyLog", toDoModel.toString())
                }
            }
        }
    }
}