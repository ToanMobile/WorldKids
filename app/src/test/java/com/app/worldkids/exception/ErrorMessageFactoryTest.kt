package com.app.worldkids.exception

import android.content.Context
import com.app.domain.exception.NoConnectedException

/**
 * Factory used to create error messages from an Exception as a condition.
 */
class ErrorMessageFactoryTest(context: Context) : ErrorMessageFactory(context) {

    override fun getError(exception: Throwable?): String =
        if (exception is NoConnectedException) "No connection"
        else "An error has occurred"
}
