package com.app.data.di

import com.app.data.net.HttpClientFactory
import com.app.data.persistence.AppDatabase
import com.app.data.persistence.DatabaseFactory
import com.app.data.repository.CardDataRepository
import com.app.domain.repository.CardRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ksp.generated.module

val netModule = module {
    single {
        HttpClientFactory().createHttpClient(context = get())
    }
}

val persistenceModule = module {
    single {
        DatabaseFactory.getDatabase(get())
    }
    single {
        get<AppDatabase>().cardDao()
    }
}

val repositoryModule = module {
    singleOf(::CardDataRepository) { bind<CardRepository>() }
}

val koinDataSourceModules = listOf(
    netModule,
    NetModule().module,
    persistenceModule,
    ProcessorModule().module,
    MapperModule().module,
    repositoryModule
)
