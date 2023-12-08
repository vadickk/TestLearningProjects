package com.test.testsqlite.core.di.modules

import android.content.Context
import com.test.testsqlite.data.db.DataBaseHelper
import com.test.testsqlite.data.db.DatabaseManager
import dagger.Module
import dagger.Provides

@Module
class DataModule(val context: Context) {

    @Provides
    fun provideDatabaseManager(dataBaseHelper: DataBaseHelper): DatabaseManager {
        return DatabaseManager(dataBaseHelper)
    }

    @Provides
    fun provideDataBaseHelper(): DataBaseHelper {
        return DataBaseHelper(context)
    }

}