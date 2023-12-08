package com.test.testsqlite.presentation

import com.test.testsqlite.data.entities.Note
import com.test.testsqlite.presentation.communication.NotesCommunication
import com.test.testsqlite.presentation.state.InputTextState

interface ManageNotes {

    fun clearTitleError()

    fun clearSubtitleError()

    fun setNewNotes(notes: List<Note>)

    fun showTitleError(errorMessage: String)

    fun showSubtitleError(errorMessage: String)

    class Base(
        val notesCommunication: NotesCommunication
    ): ManageNotes {
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
}