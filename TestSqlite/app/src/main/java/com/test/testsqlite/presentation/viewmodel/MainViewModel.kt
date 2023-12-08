package com.test.testsqlite.presentation.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.test.testsqlite.core.Init
import com.test.testsqlite.presentation.state.InputTextState
import com.test.testsqlite.presentation.communication.NotesCommunication
import com.test.testsqlite.data.entities.Note
import com.test.testsqlite.domain.ManageDatabaseRepository
import com.test.testsqlite.domain.database.DatabaseRepository
import com.test.testsqlite.domain.usecases.DeleteAllNotesFromDatabaseUseCase
import com.test.testsqlite.domain.usecases.InsertNoteToDatabaseUseCase
import com.test.testsqlite.domain.usecases.ReceiveDataFromDatabaseUseCase
import com.test.testsqlite.domain.validation.ValidNote
import com.test.testsqlite.presentation.ManageNotes
import com.test.testsqlite.presentation.communication.ObserveNotes

class MainViewModel(
    private val insertNoteToDatabaseUseCase: InsertNoteToDatabaseUseCase,
    private val receiveDataFromDatabaseUseCase: ReceiveDataFromDatabaseUseCase,
    private val deleteAllNotesFromDatabaseUseCase: DeleteAllNotesFromDatabaseUseCase,
    private val notesCommunication: NotesCommunication,
    private val validNote: ValidNote
): ViewModel(), Init, ObserveNotes, ManageNotes, ManageDatabaseRepository {

    override fun insertNoteToDatabase(note: Note) {
        if (validNote.validNote(note)) {
            insertNoteToDatabaseUseCase.execute(note)
            receiveNotesFromDatabase()
        }
    }

    override fun receiveNotesFromDatabase() {
        val notes = receiveDataFromDatabaseUseCase.execute()
        notesCommunication.manageList(notes)
    }

    override fun deleteAllNotes() {
        deleteAllNotesFromDatabaseUseCase.execute()
        receiveNotesFromDatabase()
    }

    override fun init() {
        receiveNotesFromDatabase()
    }

    override fun observeTitleError(owner: LifecycleOwner, observer: Observer<InputTextState>) =
        notesCommunication.observeTitleError(owner, observer)

    override fun observeSubtitleError(owner: LifecycleOwner, observer: Observer<InputTextState>) =
        notesCommunication.observeSubtitleError(owner, observer)

    override fun observeNotesList(owner: LifecycleOwner, observer: Observer<List<Note>>) =
        notesCommunication.observeNotesList(owner, observer)

    override fun clearTitleError() = notesCommunication.manageTitleError(InputTextState.ClearError())

    override fun clearSubtitleError() = notesCommunication.manageSubtitleError(InputTextState.ClearError())

    override fun setNewNotes(notes: List<Note>) {
        notesCommunication.manageList(notes)
    }

    override fun showTitleError(errorMessage: String) =
        notesCommunication.manageTitleError(InputTextState.ShowError(errorMessage))

    override fun showSubtitleError(errorMessage: String) =
        notesCommunication.manageSubtitleError(InputTextState.ShowError(errorMessage))
}