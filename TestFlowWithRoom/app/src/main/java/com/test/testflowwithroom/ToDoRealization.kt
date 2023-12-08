package com.test.testflowwithroom

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class ToDoRealization(private val toDoDao: ToDoDao) : ToDoRepository {
    override val allToDoesFromToDoTable: Flow<List<ToDoModel>>
        get() = toDoDao.getAllToDoWithToDoTable()

    override fun insertToToDoTable(toDoModel: ToDoModel) {
        toDoDao.insertToToDoTable(toDoModel)
    }

    override fun deleteFromToDoTable(toDoModel: ToDoModel) {
        toDoDao.deleteFromToDoTable(toDoModel)
    }


}