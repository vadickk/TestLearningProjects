package com.test.testsqlite.presentation.communication

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.test.testsqlite.core.communication.Communication
import com.test.testsqlite.presentation.state.InputTextState
import com.test.testsqlite.data.entities.Note

interface NotesCommunication : ObserveNotes {

    fun manageTitleError(inputTextState: InputTextState)

    fun manageSubtitleError(inputTextState: InputTextState)

    fun manageList(notes: List<Note>)

    class Base(
        val titleErrorCommunication: TitleErrorCommunication,
        val subtitleErrorCommunication: SubtitleErrorCommunication,
        val notesListCommunication: NotesListCommunication
    ): NotesCommunication {
        override fun manageTitleError(inputTextState: InputTextState) {
            titleErrorCommunication.map(inputTextState)
        }

        override fun manageSubtitleError(inputTextState: InputTextState) {
            subtitleErrorCommunication.map(inputTextState)
        }

        override fun manageList(notes: List<Note>) {
            notesListCommunication.map(notes)
        }

        override fun observeTitleError(owner: LifecycleOwner, observer: Observer<InputTextState>) =
            titleErrorCommunication.observe(owner, observer)

        override fun observeSubtitleError(owner: LifecycleOwner, observer: Observer<InputTextState>) =
            subtitleErrorCommunication.observe(owner, observer)

        override fun observeNotesList(owner: LifecycleOwner, observer: Observer<List<Note>>) =
            notesListCommunication.observe(owner, observer)
    }

}

interface ObserveNotes {

    fun observeTitleError(owner: LifecycleOwner, observer: Observer<InputTextState>)

    fun observeSubtitleError(owner: LifecycleOwner, observer: Observer<InputTextState>)

    fun observeNotesList(owner: LifecycleOwner, observer: Observer<List<Note>>)
}

interface TitleErrorCommunication: Communication.Mutable<InputTextState> {
    class Base: Communication.Ui<InputTextState>(), TitleErrorCommunication
}

interface SubtitleErrorCommunication: Communication.Mutable<InputTextState> {
    class Base: Communication.Ui<InputTextState>(), SubtitleErrorCommunication
}

interface NotesListCommunication: Communication.Mutable<List<Note>> {
    class Base: Communication.Ui<List<Note>>(), NotesListCommunication
}