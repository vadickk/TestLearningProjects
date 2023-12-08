package com.test.testflowwithroom

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    init {
        val daoToDo = ToDoDataBase.getInstance(application).getToDoDao()
        REPOSITORY = ToDoRealization(daoToDo)
    }
    // second decision
    private val _some1 = MutableStateFlow<List<ToDoModel>>(listOf())
    var some1: SharedFlow<List<ToDoModel>> = _some1.asSharedFlow()

    private val _some = MutableStateFlow<List<ToDoModel>>(listOf())
    var some: StateFlow<List<ToDoModel>> = _some.asStateFlow()
    //first decision
//    fun getAllToDo() = flow {
//        REPOSITORY.allToDoesFromToDoTable.collect {
//            emit(it)
//        }
//    }
    //second decision
    suspend fun getAllToDo() {
        REPOSITORY.allToDoesFromToDoTable.collect {
            //_some1.value = it
            _some.value = it
        }
    }
    fun insert(toDoModel: ToDoModel) {
        viewModelScope.launch {
            REPOSITORY.insertToToDoTable(toDoModel)
        }
    }
}