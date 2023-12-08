package com.test.testsqlite.core.di.modules

import com.test.testsqlite.data.database.DatabaseRealization
import com.test.testsqlite.data.db.DatabaseManager
import com.test.testsqlite.domain.database.DatabaseRepository
import com.test.testsqlite.domain.usecases.DeleteAllNotesFromDatabaseUseCase
import com.test.testsqlite.domain.usecases.InsertNoteToDatabaseUseCase
import com.test.testsqlite.domain.usecases.ReceiveDataFromDatabaseUseCase
import com.test.testsqlite.domain.validation.ValidNote
import com.test.testsqlite.presentation.ManageNotes
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideInsertNoteToDatabaseUseCase(databaseRepository: DatabaseRepository): InsertNoteToDatabaseUseCase {
        return InsertNoteToDatabaseUseCase(databaseRepository)
    }

    @Provides
    fun provideReceiveDataFromDatabaseUseCase(databaseRepository: DatabaseRepository): ReceiveDataFromDatabaseUseCase {
        return ReceiveDataFromDatabaseUseCase(databaseRepository)
    }

    @Provides
    fun provideDeleteAllNotesFromDatabaseUseCase(databaseRepository: DatabaseRepository): DeleteAllNotesFromDatabaseUseCase {
        return DeleteAllNotesFromDatabaseUseCase(databaseRepository)
    }

    @Provides
    fun provideDatabaseRepository(dataBaseManager: DatabaseManager): DatabaseRepository {
        return DatabaseRealization(dataBaseManager)
    }

    @Provides
    fun provideValidNote(manageNotes: ManageNotes): ValidNote {
        return ValidNote.Base(manageNotes = manageNotes)
    }
}