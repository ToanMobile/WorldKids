package com.app.worldkids.data.di

import com.app.worldkids.data.repository.NetworkRepository
import com.app.worldkids.data.repository.NetworkRepositoryImpl
import com.app.worldkids.ui.screen.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val dataModule = module {
    single<NetworkRepository> { NetworkRepositoryImpl() }
}

val viewModelModule = module {
    viewModelOf(::MainViewModel)
}