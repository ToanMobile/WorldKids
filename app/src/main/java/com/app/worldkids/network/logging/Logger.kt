/*
 * *Created by HuraTeamAndroid on 2022
 * Company: Netacom.
 *  *
 */

package com.app.worldkids.network.logging

interface Logger {
    fun log(level: Int, tag: String, msg: String)

    companion object {

        val DEFAULT: Logger = object : Logger {
            override fun log(level: Int, tag: String, msg: String) {
//                Platform.get().log(level, message, null)
            }
        }
    }
}
