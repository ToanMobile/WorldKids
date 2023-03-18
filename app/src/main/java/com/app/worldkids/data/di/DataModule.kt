package com.app.worldkids.data.di

import com.app.worldkids.data.repository.NetworkRepository
import com.app.worldkids.data.repository.NetworkRepositoryImpl
import com.app.worldkids.ui.screen.main.MainViewModel
import com.app.worldkids.utils.CoroutineDispatchers
import com.app.worldkids.utils.DefaultCoroutineDispatchers
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    singleOf(::DefaultCoroutineDispatchers) bind CoroutineDispatchers::class
    single<NetworkRepository> { NetworkRepositoryImpl() }
}

val viewModelModule = module {
    viewModelOf(::MainViewModel)
}