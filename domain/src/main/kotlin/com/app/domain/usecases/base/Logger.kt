package com.app.domain.usecases.base

/**
 * Copyright (C) 2023 ToanMobile
 * Licensed under the Apache License Version 2.0
 */
interface Logger {
    fun log(tag: String, message: () -> String)
    fun logError(tag: String, throwable: () -> String)
}
