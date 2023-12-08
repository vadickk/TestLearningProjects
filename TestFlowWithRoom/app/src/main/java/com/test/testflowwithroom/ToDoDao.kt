package com.test.testflowwithroom

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface ToDoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertToToDoTable(toDoModel: ToDoModel)

    @Delete
    fun deleteFromToDoTable(toDoModel: ToDoModel)

    @Query("SELECT * FROM todo_table")
    fun getAllToDoWithToDoTable() : Flow<List<ToDoModel>>

}