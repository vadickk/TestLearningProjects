package com.test.testsqlite.domain

import com.test.testsqlite.data.entities.Note

interface ManageDatabaseRepository {

    fun insertNoteToDatabase(note: Note)

    fun receiveNotesFromDatabase()

    fun deleteAllNotes()
}