package com.test.testsqlite.domain.usecases

import com.test.testsqlite.domain.database.DatabaseRepository

class DeleteAllNotesFromDatabaseUseCase(
    val databaseRepository: DatabaseRepository
) {

    fun execute() {
        databaseRepository.deleteAllNotes()
    }
}