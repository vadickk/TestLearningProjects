package com.test.testsqlite.domain.usecases

import com.test.testsqlite.data.entities.Note
import com.test.testsqlite.domain.database.DatabaseRepository

class ReceiveDataFromDatabaseUseCase(
    private val databaseRepository: DatabaseRepository
) {

    fun execute(): List<Note> {
        return databaseRepository.receiveNotesFromDatabase()
    }
}