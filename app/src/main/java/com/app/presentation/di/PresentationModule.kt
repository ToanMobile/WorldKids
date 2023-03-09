package com.app.presentation.di

import android.util.Log
import com.app.domain.usecases.base.Logger
import com.app.presentation.exception.ErrorMessageFactory
import com.app.presentation.scenes.carddetail.CardDetailViewModel
import com.app.presentation.scenes.home.CardListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val helperModule = module {
    single<Logger> {
        object : Logger {
            override fun log(tag: String, message: () -> String) {
                Log.d(tag, message())
            }

            override fun logError(tag: String, throwable: () -> String) {
                Log.e(tag, throwable())
            }
        }
    }
    singleOf(::ErrorMessageFactory)
}

val viewModelModule = module {
    viewModelOf(::CardListViewModel)
    viewModelOf(::CardDetailViewModel)
}

val koinPresentationModules = listOf(
    helperModule,
    viewModelModule
)
