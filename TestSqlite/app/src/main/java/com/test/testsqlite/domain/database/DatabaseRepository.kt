package com.test.testsqlite.domain.database

import com.test.testsqlite.data.entities.Note

interface DatabaseRepository {

    fun insertNoteToDatabase(note: Note)

    fun receiveNotesFromDatabase(): List<Note>

    fun deleteAllNotes()
}