package com.test.testflowwithroom

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface ToDoRepository {

    //TODO Start Fragment
    val allToDoesFromToDoTable: Flow<List<ToDoModel>>

    fun insertToToDoTable(toDoModel: ToDoModel)

    fun deleteFromToDoTable(toDoModel: ToDoModel)

}