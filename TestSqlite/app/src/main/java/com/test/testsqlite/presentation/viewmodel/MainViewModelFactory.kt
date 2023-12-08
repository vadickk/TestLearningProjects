package com.test.testsqlite.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.testsqlite.domain.usecases.DeleteAllNotesFromDatabaseUseCase
import com.test.testsqlite.domain.usecases.InsertNoteToDatabaseUseCase
import com.test.testsqlite.domain.usecases.ReceiveDataFromDatabaseUseCase
import com.test.testsqlite.domain.validation.ValidNote
import com.test.testsqlite.presentation.ManageNotes
import com.test.testsqlite.presentation.communication.NotesCommunication
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(
    val insertNoteToDatabaseUseCase: InsertNoteToDatabaseUseCase,
    val receiveDataFromDatabaseUseCase: ReceiveDataFromDatabaseUseCase,
    val deleteAllNotesFromDatabaseUseCase: DeleteAllNotesFromDatabaseUseCase,
    val notesCommunication: NotesCommunication,
    val validNote: ValidNote
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            insertNoteToDatabaseUseCase = insertNoteToDatabaseUseCase,
            receiveDataFromDatabaseUseCase = receiveDataFromDatabaseUseCase,
            deleteAllNotesFromDatabaseUseCase = deleteAllNotesFromDatabaseUseCase,
            notesCommunication = notesCommunication,
            validNote = validNote
        ) as T
    }
}