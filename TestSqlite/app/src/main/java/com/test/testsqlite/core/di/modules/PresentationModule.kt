package com.test.testsqlite.core.di.modules

import com.test.testsqlite.domain.usecases.DeleteAllNotesFromDatabaseUseCase
import com.test.testsqlite.domain.usecases.InsertNoteToDatabaseUseCase
import com.test.testsqlite.domain.usecases.ReceiveDataFromDatabaseUseCase
import com.test.testsqlite.domain.validation.ValidNote
import com.test.testsqlite.presentation.ManageNotes
import com.test.testsqlite.presentation.communication.NotesCommunication
import com.test.testsqlite.presentation.communication.NotesListCommunication
import com.test.testsqlite.presentation.communication.SubtitleErrorCommunication
import com.test.testsqlite.presentation.communication.TitleErrorCommunication
import com.test.testsqlite.presentation.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {

    @Provides
    fun provideMainViewModelFactory(
        insertNoteToDatabaseUseCase: InsertNoteToDatabaseUseCase,
        receiveDataFromDatabaseUseCase: ReceiveDataFromDatabaseUseCase,
        deleteAllNotesFromDatabaseUseCase: DeleteAllNotesFromDatabaseUseCase,
        notesCommunication: NotesCommunication,
        validNote: ValidNote
    ): MainViewModelFactory {
        return MainViewModelFactory(
            insertNoteToDatabaseUseCase = insertNoteToDatabaseUseCase,
            receiveDataFromDatabaseUseCase = receiveDataFromDatabaseUseCase,
            deleteAllNotesFromDatabaseUseCase = deleteAllNotesFromDatabaseUseCase,
            notesCommunication = notesCommunication,
            validNote
        )
    }

    @Provides
    fun provideNotesCommunication(
        titleErrorCommunication: TitleErrorCommunication,
        subtitleErrorCommunication: SubtitleErrorCommunication,
        notesListCommunication: NotesListCommunication
    ): NotesCommunication {
        return NotesCommunication.Base(
            titleErrorCommunication = titleErrorCommunication,
            subtitleErrorCommunication = subtitleErrorCommunication,
            notesListCommunication = notesListCommunication
        )
    }

    @Provides
    fun provideTitleErrorCommunication(): TitleErrorCommunication {
        return TitleErrorCommunication.Base()
    }

    @Provides
    fun provideSubtitleErrorCommunication(): SubtitleErrorCommunication {
        return SubtitleErrorCommunication.Base()
    }

    @Provides
    fun provideNotesListCommunication(): NotesListCommunication {
        return NotesListCommunication.Base()
    }

    @Provides
    fun provideManageNotes(notesCommunication: NotesCommunication): ManageNotes {
        return ManageNotes.Base(notesCommunication)
    }

}