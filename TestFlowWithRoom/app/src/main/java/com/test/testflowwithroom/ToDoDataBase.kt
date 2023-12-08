package com.test.testflowwithroom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ToDoModel::class], version = 2)
abstract class ToDoDataBase : RoomDatabase(){
    abstract fun getToDoDao() : ToDoDao

    companion object {
        private var database: ToDoDataBase? = null

        @Synchronized
        fun getInstance(context: Context) : ToDoDataBase {
            return if (database == null) {
                database = Room.databaseBuilder(context, ToDoDataBase::class.java, "db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                database as ToDoDataBase
            } else {
                database as ToDoDataBase
            }
        }

    }
}