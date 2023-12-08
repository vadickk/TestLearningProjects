package com.test.testsqlite.domain.usecases

import com.test.testsqlite.data.entities.Note
import com.test.testsqlite.domain.database.DatabaseRepository

class InsertNoteToDatabaseUseCase(
    private val databaseRepository: DatabaseRepository
) {

    fun execute(note: Note) {
        //todo try/catch
        databaseRepository.insertNoteToDatabase(note)
    }
}