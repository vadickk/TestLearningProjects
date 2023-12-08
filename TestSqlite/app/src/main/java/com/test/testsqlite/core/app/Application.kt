package com.test.testsqlite.core.app

import android.app.Application
import com.test.testsqlite.core.di.component.AppComponent
import com.test.testsqlite.core.di.component.DaggerAppComponent
import com.test.testsqlite.core.di.modules.CoreModule
import com.test.testsqlite.core.di.modules.DataModule
import com.test.testsqlite.core.di.modules.DomainModule
import com.test.testsqlite.core.di.modules.PresentationModule

class Application: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .coreModule(CoreModule())
            .dataModule(DataModule(this))
            .domainModule(DomainModule())
            .presentationModule(PresentationModule())
            .build()
    }

}