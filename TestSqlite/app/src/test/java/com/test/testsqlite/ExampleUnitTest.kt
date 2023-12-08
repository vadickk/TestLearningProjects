package com.test.testsqlite

import com.test.testsqlite.data.entities.Note
import com.test.testsqlite.domain.database.DatabaseRepository
import com.test.testsqlite.domain.usecases.DeleteAllNotesFromDatabaseUseCase
import com.test.testsqlite.domain.usecases.InsertNoteToDatabaseUseCase
import com.test.testsqlite.domain.usecases.ReceiveDataFromDatabaseUseCase
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.mockito.Mockito
import org.mockito.kotlin.mock

class ExampleUnitTest {

    private val databaseRepository = mock<DatabaseRepository>()

    @AfterEach
    fun tearDown() {
        Mockito.reset(databaseRepository)
    }

    @Test
    fun `should return true if insert to database was successful`() {
        val note = Note("Hello", "worldd")

        Mockito.`when`(databaseRepository.receiveNotesFromDatabase()).thenReturn(listOf(note))

        val receiveDataFromDatabaseUseCase = ReceiveDataFromDatabaseUseCase(databaseRepository)
        val insertNoteToDatabaseUseCase = InsertNoteToDatabaseUseCase(databaseRepository)

        insertNoteToDatabaseUseCase.execute(note)
        val list = receiveDataFromDatabaseUseCase.execute()
        val result = list.first() == note

        Assertions.assertEquals(true, result)
    }

    @Test
    fun `should delete all notes from database`() {
        val note1 = Note("title1", "subtitle1")
        val note2 = Note("title2", "subtitle2")

        val listOfNotes = mutableListOf(note1, note2)

        Mockito.`when`(databaseRepository.deleteAllNotes()).thenReturn(listOfNotes.clear())

        val deleteAllNotesFromDatabaseUseCase = DeleteAllNotesFromDatabaseUseCase(databaseRepository)
        val insertNoteToDatabaseUseCase = InsertNoteToDatabaseUseCase(databaseRepository)

        insertNoteToDatabaseUseCase.execute(note1)
        insertNoteToDatabaseUseCase.execute(note2)

        deleteAllNotesFromDatabaseUseCase.execute()

        Assertions.assertEquals(listOf<Note>(), listOfNotes)
    }
}