/*
 * *Created by HuraTeamAndroid on 2022
 * Company: Netacom.
 *  *
 */

package com.app.worldkids.network.logging

import okhttp3.internal.platform.Platform
import java.util.logging.Level

internal class I private constructor() {

    init {
        throw UnsupportedOperationException()
    }

    companion object {
        private val prefix = listOf(". ", " .")
        private var index = 0

        fun log(type: Int, tag: String, msg: String, isLogHackEnable: Boolean) {
            if (msg.length > 4000) {
                val chunkCount = msg.length / 4000 // integer division
                for (i in 0..chunkCount) {
                    val max = 4000 * (i + 1)
                    if (max >= msg.length) {
                        val finalTag = getFinalTag(tag, isLogHackEnable)
                        val logger = java.util.logging.Logger.getLogger(
                            if (isLogHackEnable) finalTag else tag
                        )
                        when (type) {
                            Platform.INFO -> logger.log(Level.INFO, msg.substring(4000 * i))
                            else -> logger.log(Level.SEVERE, msg.substring(4000 * i))
                        }
                    } else {
                        val finalTag = getFinalTag(tag, isLogHackEnable)
                        val logger = java.util.logging.Logger.getLogger(
                            if (isLogHackEnable) finalTag else tag
                        )
                        when (type) {
                            Platform.INFO -> logger.log(Level.INFO, msg.substring(4000 * i), max)
                            else -> logger.log(Level.SEVERE, msg.substring(4000 * i), max)
                        }
                    }
                }
            }
        }

        private fun getFinalTag(tag: String, isLogHackEnable: Boolean): String {
            return if (isLogHackEnable) {
                index = index xor 1
                prefix[index] + tag
            } else {
                tag
            }
        }
    }
}
