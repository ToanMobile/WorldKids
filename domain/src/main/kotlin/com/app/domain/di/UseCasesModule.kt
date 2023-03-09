package com.app.domain.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan("com.app.domain.usecases")
class UseCasesModule
