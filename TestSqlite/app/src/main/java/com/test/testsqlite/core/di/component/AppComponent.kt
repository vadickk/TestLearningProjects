package com.test.testsqlite.core.di.component

import com.test.testsqlite.core.di.modules.CoreModule
import com.test.testsqlite.core.di.modules.DataModule
import com.test.testsqlite.core.di.modules.DomainModule
import com.test.testsqlite.core.di.modules.PresentationModule
import com.test.testsqlite.presentation.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PresentationModule::class, DataModule::class, DomainModule::class, CoreModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}