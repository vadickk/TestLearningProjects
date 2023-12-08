package com.test.testsqlite.data.database

import com.test.testsqlite.data.db.DatabaseManager
import com.test.testsqlite.data.entities.Note
import com.test.testsqlite.domain.database.DatabaseRepository

class DatabaseRealization(private val dataBaseManager: DatabaseManager): DatabaseRepository {
    override fun insertNoteToDatabase(note: Note) {
        dataBaseManager.insertNote(note)
    }

    override fun receiveNotesFromDatabase(): List<Note> {
        return dataBaseManager.receiveNotes()
    }

    override fun deleteAllNotes() {
        dataBaseManager.deleteNotesFromDataBase()
    }

}