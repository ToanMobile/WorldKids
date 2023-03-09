package com.app.data.di

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module

@Module
@ComponentScan("com.app.data.persistence.processor")
class ProcessorModule
