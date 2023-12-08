package com.test.testmyserverktor.di

import com.test.testmyserverktor.repostitory.Repository
import com.test.testmyserverktor.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel {
        MainViewModel(repository = get())
    }

    single {
        Repository()
    }

}