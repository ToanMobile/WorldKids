/*
 * *Created by HuraTeamAndroid on 2022
 * Company: Netacom.
 *  *
 */

package com.app.worldkids.network.logging

import okhttp3.Request
import java.io.IOException

interface BufferListener {
    @Throws(IOException::class)
    fun getJsonResponse(request: Request): String
}
