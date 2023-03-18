/*
 * *Created by HuraTeamAndroid on 2022
 * Company: Netacom.
 *  *
 */

package com.app.worldkids.network.logging

import okhttp3.Request
import okio.Buffer
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

internal class Printer protected constructor() {

    init {
        throw UnsupportedOperationException()
    }

    companion object {

        private val JSON_INDENT = 3

        private val LINE_SEPARATOR = System.getProperty("line.separator") ?: ""
        private val DOUBLE_SEPARATOR = LINE_SEPARATOR + LINE_SEPARATOR

        private val OMITTED_RESPONSE = listOf(LINE_SEPARATOR, "")
        private val OMITTED_REQUEST = listOf(LINE_SEPARATOR, "")
        var logStringHeader = StringBuffer()
        private val N = "\n"
        private val T = "\t"
        private val BODY_TAG = "Body:"
        private val URL_TAG = "URL: "
        private val METHOD_TAG = "Method: @"
        private val HEADERS_TAG = "Headers:"
        private val STATUS_CODE_TAG = "Status Code: "
        private val RECEIVED_TAG = "Received in: "
        var CORNER_UP = "┌ "
        var CORNER_BOTTOM = "└ "
        var CENTER_LINE = "├ "
        var DEFAULT_LINE = "│ "
        var REQUEST_UP_LINE =
            "┌────── Request ────────────────────────────────────────────────────────────────────────"
        var END_LINE =
            "└───────────────────────────────────────────────────────────────────────────────────────"
        var RESPONSE_UP_LINE =
            "┌────── Response ───────────────────────────────────────────────────────────────────────"

        private fun isEmpty(line: String): Boolean {
            return line.isEmpty() || N == line || T == line || line.trim { it <= ' ' }.isEmpty()
        }

        fun printJsonRequest(builder: LoggingInterceptor.Builder, request: Request) {
            val requestBody = LINE_SEPARATOR + BODY_TAG + LINE_SEPARATOR + bodyToString(request)
            builder.getTag(true)
            if (builder.logger == null) {
                logStringHeader.append(REQUEST_UP_LINE + N)
            }
            logStringHeader.append(listOf(URL_TAG + request.url) + N)
            logStringHeader.append(getRequest(request, builder.getLevel()) + N)
            if (builder.getLevel() === Level.BASIC || builder.getLevel() === Level.BODY || builder.getLevel() === Level.CloudHMS) {
                logLines(
                    requestBody.split(LINE_SEPARATOR.toRegex()).dropLastWhile { it.isEmpty() }
                        .toList(),
                    builder.logger,
                    true
                )
            }
            if (builder.logger == null) {
                logStringHeader.append(END_LINE + N)
            }
            // I.log(builder.com.netacom.com.netacom.base.chat.type, tag, END_LINE, builder.isLogHackEnable)
        }

        fun printJsonResponse(
            builder: LoggingInterceptor.Builder,
            chainMs: Long,
            isSuccessful: Boolean,
            code: Int,
            headers: String,
            bodyString: String,
            segments: List<String>,
            message: String,
            responseUrl: String
        ) {
            val responseBody =
                LINE_SEPARATOR + BODY_TAG + LINE_SEPARATOR + getJsonString(bodyString)
            val tag = builder.getTag(false)
            val response = getResponse(
                headers,
                chainMs,
                code,
                isSuccessful,
                builder.getLevel(),
                segments,
                message
            )
            if (builder.logger == null) {
                logStringHeader.append(REQUEST_UP_LINE + N)
                // I.log(builder.com.netacom.com.netacom.base.chat.type, tag, RESPONSE_UP_LINE, builder.isLogHackEnable)
            }
            logStringHeader.append(URL_TAG + responseUrl + N)
            response.map {
                logStringHeader.append(it + N)
            }
            if (builder.getLevel() === Level.BASIC || builder.getLevel() === Level.BODY || builder.getLevel() === Level.CloudHMS) {
                logLines(
                    responseBody.split(LINE_SEPARATOR.toRegex()).dropLastWhile { it.isEmpty() }
                        .toList(),
                    builder.logger,
                    true
                )
            }
            if (builder.logger == null) {
                logStringHeader.append(END_LINE + N)
                // I.log(builder.com.netacom.com.netacom.base.chat.type, tag, END_LINE, builder.isLogHackEnable)
            }
            I.log(builder.type, tag, logStringHeader.toString(), builder.isLogHackEnable)
        }

        fun printFileRequest(builder: LoggingInterceptor.Builder, request: Request) {
            builder.getTag(true)
            if (builder.logger == null) {
                logStringHeader.append(REQUEST_UP_LINE + N)
            }
            // I.log(builder.com.netacom.com.netacom.base.chat.type, tag, REQUEST_UP_LINE, builder.isLogHackEnable)
            logLines(
                listOf(URL_TAG + request.url),
                builder.logger,
                false
            )
            logLines(
                getRequest(request, builder.getLevel()),
                builder.logger,
                true
            )
            if (builder.getLevel() === Level.BASIC || builder.getLevel() === Level.BODY) {
                logLines(
                    OMITTED_REQUEST,
                    builder.logger,
                    true
                )
            }
            if (builder.logger == null) {
                logStringHeader.append(END_LINE + N)
            }
            // I.log(builder.com.netacom.com.netacom.base.chat.type, tag, END_LINE, builder.isLogHackEnable)
        }

        fun printFileResponse(
            builder: LoggingInterceptor.Builder,
            chainMs: Long,
            isSuccessful: Boolean,
            code: Int,
            headers: String,
            segments: List<String>,
            message: String
        ) {
            val tag = builder.getTag(false)
            if (builder.logger == null) {
                logStringHeader.append(RESPONSE_UP_LINE + N)
            }
            // I.log(builder.com.netacom.com.netacom.base.chat.type, tag, RESPONSE_UP_LINE, builder.isLogHackEnable)
            logLines(
                getResponse(
                    headers,
                    chainMs,
                    code,
                    isSuccessful,
                    builder.getLevel(),
                    segments,
                    message
                ),
                builder.logger,
                true
            )
            logLines(
                OMITTED_RESPONSE,
                builder.logger,
                true
            )
            if (builder.logger == null) {
                logStringHeader.append(END_LINE + N)
            }
            builder.logger?.log(builder.type, tag, logStringHeader.toString())
        }

        private fun getRequest(request: Request, level: Level): List<String> {
            val log: String
            val header = request.headers.toString()
            val loggableHeader =
                level === Level.HEADERS || level === Level.BASIC || level === Level.CloudHMS
            log = METHOD_TAG + request.method + DOUBLE_SEPARATOR +
                if (isEmpty(header)) {
                    ""
                } else if (loggableHeader) {
                    HEADERS_TAG + LINE_SEPARATOR + dotHeaders(
                        header
                    )
                } else {
                    ""
                }
            return log.split(LINE_SEPARATOR.toRegex()).dropLastWhile { it.isEmpty() }.toList()
        }

        private fun getResponse(
            header: String,
            tookMs: Long,
            code: Int,
            isSuccessful: Boolean,
            level: Level,
            segments: List<String>,
            message: String
        ): List<String> {
            val log: String
            val loggableHeader =
                level === Level.HEADERS || level === Level.BASIC // Not show Header || level === Level.CloudHMS
            val segmentString = slashSegments(segments)
            log =
                (
                    (if (segmentString.isNotEmpty()) "$segmentString - " else "") + "is success : " +
                        isSuccessful + " - " + RECEIVED_TAG + tookMs + "ms" + DOUBLE_SEPARATOR + STATUS_CODE_TAG +
                        code + " / " + message + DOUBLE_SEPARATOR + if (isEmpty(header)) {
                        ""
                    } else if (loggableHeader) {
                        HEADERS_TAG + LINE_SEPARATOR +
                            dotHeaders(header)
                    } else {
                        ""
                    }
                    )
            return log.split(LINE_SEPARATOR.toRegex()).dropLastWhile { it.isEmpty() }.toList()
        }

        private fun slashSegments(segments: List<String>): String {
            val segmentString = StringBuilder()
            for (segment in segments) {
                segmentString.append("/").append(segment)
            }
            return segmentString.toString()
        }

        private fun dotHeaders(header: String): String {
            val headers =
                header.split(LINE_SEPARATOR.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val builder = StringBuilder()
            var tag = "─ "
            if (headers.size > 1) {
                for (i in headers.indices) {
                    tag = if (i == 0) {
                        CORNER_UP
                    } else if (i == headers.size - 1) {
                        CORNER_BOTTOM
                    } else {
                        CENTER_LINE
                    }
                    builder.append(tag).append(headers[i]).append("\n")
                }
            } else {
                for (item in headers) {
                    builder.append(tag).append(item).append("\n")
                }
            }
            return builder.toString()
        }

        private fun logLines(
            lines: List<String>,
            logger: Logger?,
            withLineSize: Boolean
        ) {
            for (line in lines) {
                val lineLength = line.length
                val MAX_LONG_SIZE = if (withLineSize) 170 else lineLength
                for (i in 0..lineLength / MAX_LONG_SIZE) {
                    val start = i * MAX_LONG_SIZE
                    var end = (i + 1) * MAX_LONG_SIZE
                    end = if (end > line.length) line.length else end
                    if (logger == null) {
                        logStringHeader.append(DEFAULT_LINE + line.substring(start, end) + N)
                    } else {
                        logStringHeader.append(line.substring(start, end) + N)
                    }
                }
            }
        }

        private fun bodyToString(request: Request): String {
            try {
                val copy = request.newBuilder().build()
                val buffer = Buffer()
                val body = copy.body ?: return ""
                body.writeTo(buffer)
                return getJsonString(buffer.readUtf8())
            } catch (e: IOException) {
                return "{\"err\": \"" + e.message + "\"}"
            }
        }

        fun getJsonString(msg: String): String {
            var message: String
            try {
                if (msg.startsWith("{")) {
                    val jsonObject = JSONObject(msg)
                    message = jsonObject.toString(JSON_INDENT)
                } else if (msg.startsWith("[")) {
                    val jsonArray = JSONArray(msg)
                    message = jsonArray.toString(JSON_INDENT)
                } else {
                    message = msg
                }
            } catch (e: JSONException) {
                message = msg
            } catch (e1: OutOfMemoryError) {
                message = "OutOfMemoryError" // OOM_OMITTED
            }
            return message
        }
    }
}
