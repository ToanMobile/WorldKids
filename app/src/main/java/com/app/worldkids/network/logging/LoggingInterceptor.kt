/*
 * *Created by HuraTeamAndroid on 2022
 * Company: Netacom.
 *  *
 */

package com.app.worldkids.network.logging

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.platform.Platform
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit

class LoggingInterceptor private constructor(private val builder: Builder) : Interceptor {

    private val isDebug: Boolean
    private val utf8 = Charset.forName("UTF-8")

    init {
        this.isDebug = builder.isDebug
    }

    @Throws(IOException::class, Exception::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val connection = chain.connection()
        val protocol = connection?.protocol() ?: Protocol.HTTP_1_1

        val requestBuilder = request.newBuilder().build()
        val bufferRequest = Buffer()
        requestBuilder.body?.writeTo(bufferRequest)

        var requestStartMessage = "--> ${request.method} ${request.url} $protocol"

        request.body?.let {
            requestStartMessage = String.format(
                "%s\nContent-Header:: \n%sContent-Type:: %s\nContent-Length:: %s\nContent-Body:: %s",
                requestStartMessage,
                request.headers.toString(),
                it.contentType(),
                it.contentLength(),
                bufferRequest.readUtf8()
            )
        }

        Log.i("INFO", requestStartMessage)

        val startNs = System.nanoTime()
        val response = chain.proceed(request)
        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)

        val responseBody = response.body
        responseBody?.let {
            val contentLength = responseBody.contentLength()
            val bodySize = if (contentLength != -1L) "$contentLength-byte" else "unknown-length"

            Log.i("INFO", response.headers.toString())
            Log.i(
                "INFO",
                "<-- ${response.code} ${response.message} ${response.request.url} (${tookMs}ms, $bodySize)"
            )

            val source = responseBody.source()
            source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source.buffer

            var charset: Charset = utf8
            responseBody.contentType()?.let {
                try {
                    charset = it.charset(utf8)!!
                } catch (e: Exception) {
                    Log.i("INFO", "")
                    Log.i("INFO", "Couldn't decode the response body; charset is likely malformed.")
                    Log.i("INFO", "${e.message}")
                    Log.i("INFO", "<-- END HTTP")
                }
            }

            if (contentLength != 0L) {
                Log.i("INFO", "")
                Log.i("INFO", buffer.clone().readString(charset))
            }
            Log.i("INFO", "<-- END HTTP (${buffer.size}-byte body)")
        }

        return response
//        val buffer = Buffer()
//        var request = chain.request()
//        val headerMap = builder.headers
//        if (headerMap.size > 0) {
//            val requestBuilder = request.newBuilder()
//            for (key in headerMap.keys) {
//                val value = headerMap[key]
//                requestBuilder.addHeader(key, value!!)
//            }
//            request = requestBuilder.build()
//        }
//        val queryMap = builder.httpUrl
//        if (queryMap.size > 0) {
//            val httpUrlBuilder = request.url.newBuilder(request.url.toString())
//            for (key in queryMap.keys) {
//                val value = queryMap[key]
//                httpUrlBuilder!!.addQueryParameter(key, value)
//            }
//            request = request.newBuilder().url(httpUrlBuilder!!.build()).build()
//        }
//
//        if (!isDebug || builder.getLevel() === Level.NONE) {
//            return chain.proceed(request)
//        }
//        //Config export json view
//        if (builder.getLevel() == Level.CloudHMS) {
//            Printer.CORNER_UP = " "
//            Printer.CORNER_BOTTOM = " "
//            Printer.CENTER_LINE = " "
//            Printer.DEFAULT_LINE = " "
//            Printer.REQUEST_UP_LINE = "Request"
//            Printer.END_LINE = " "
//            Printer.RESPONSE_UP_LINE = "Response"
//        } else {
//            Printer.CORNER_UP = "┌ "
//            Printer.CORNER_BOTTOM = "└ "
//            Printer.CENTER_LINE = "├ "
//            Printer.DEFAULT_LINE = "│ "
//            Printer.REQUEST_UP_LINE = "┌────── Request ────────────────────────────────────────────────────────────────────────"
//            Printer.END_LINE = "└───────────────────────────────────────────────────────────────────────────────────────"
//            Printer.RESPONSE_UP_LINE = "┌────── Response ───────────────────────────────────────────────────────────────────────"
//        }
//        val requestBody = request.body
//        var rSubtype: String? = null
//        if (requestBody?.contentType() != null) {
//            rSubtype = requestBody.contentType()!!.subtype
//        }
//        val executor = builder.executor
//        Printer.logStringHeader.apply {
//            delete(0, length)
//        }
//        buffer.clear()
//        if (isNotFileRequest(rSubtype)) {
//            if (executor != null) {
//                executor.execute(createPrintJsonRequestRunnable(builder, request))
//            } else {
//                Printer.printJsonRequest(builder, request)
//            }
//        } else {
//            if (executor != null) {
//                executor.execute(createFileRequestRunnable(builder, request))
//            } else {
//                Printer.printFileRequest(builder, request)
//            }
//        }
//        //Get Query
//        requestBody?.writeTo(buffer)
//        val charset = requestBody?.contentType()?.charset(StandardCharsets.UTF_8)
//        if (buffer.isProbablyUtf8() && charset != null) {
//            Printer.logStringHeader.append("Query=${buffer.readString(charset)} \n")
//        }else{
//            Printer.logStringHeader.append("NoQuery\n")
//        }
//        val st = System.nanoTime()
//        val response: Response
//        if (builder.isMockEnabled) {
//            try {
//                TimeUnit.MILLISECONDS.sleep(builder.sleepMs)
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//            }
//
//            response = Response.Builder()
//                .body(
//                    builder.listener?.getJsonResponse(request)?.toResponseBody("application/json".toMediaTypeOrNull())
//                )
//                .request(chain.request())
//                .protocol(Protocol.HTTP_1_1)
//                .message("Mock")
//                .code(200)
//                .build()
//        } else {
//            response = chain.proceed(request)
//        }
//        val chainMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - st)
//
//        val segmentList = request.url.encodedPathSegments
//        val header = response.headers.toString()
//        val code = response.code
//        val isSuccessful = response.isSuccessful
//        val message = response.message
//        val responseBody = response.body
//        val contentType = responseBody!!.contentType()
//
//        var subtype: String? = null
//        val body: ResponseBody
//
//        if (contentType != null) {
//            subtype = contentType.subtype
//        }
//        if (isNotFileRequest(subtype)) {
//            val bodyString = Printer.getJsonString(responseBody.string())
//            val url = response.request.url.toString()
//            if (executor != null) {
//                executor.execute(
//                    createPrintJsonResponseRunnable(
//                        builder, chainMs, isSuccessful, code, header, bodyString,
//                        segmentList, message, url
//                    )
//                )
//            } else {
//                Printer.printJsonResponse(
//                    builder, chainMs, isSuccessful, code, header, bodyString,
//                    segmentList, message, url
//                )
//            }
//            body = ResponseBody.create(contentType, bodyString)
//        } else {
//            if (executor != null) {
//                executor.execute(
//                    createFileResponseRunnable(
//                        builder,
//                        chainMs,
//                        isSuccessful,
//                        code,
//                        header,
//                        segmentList,
//                        message
//                    )
//                )
//            } else {
//                Printer.printFileResponse(
//                    builder,
//                    chainMs,
//                    isSuccessful,
//                    code,
//                    header,
//                    segmentList,
//                    message
//                )
//            }
//            return response
//        }
//        return response.newBuilder().body(body).build()
    }

    private fun isNotFileRequest(subtype: String?): Boolean {
        return subtype != null && (
            subtype.contains("json") ||
                subtype.contains("xml") ||
                subtype.contains("plain") ||
                subtype.contains("html")
            )
    }

    private fun createPrintJsonRequestRunnable(builder: Builder, request: Request): Runnable {
        return Runnable { Printer.printJsonRequest(builder, request) }
    }

    private fun createFileRequestRunnable(builder: Builder, request: Request): Runnable {
        return Runnable { Printer.printFileRequest(builder, request) }
    }

    private fun createPrintJsonResponseRunnable(
        builder: Builder,
        chainMs: Long,
        isSuccessful: Boolean,
        code: Int,
        headers: String,
        bodyString: String,
        segments: List<String>,
        message: String,
        responseUrl: String
    ): Runnable {
        return Runnable {
            Printer.printJsonResponse(
                builder,
                chainMs,
                isSuccessful,
                code,
                headers,
                bodyString,
                segments,
                message,
                responseUrl
            )
        }
    }

    private fun createFileResponseRunnable(
        builder: Builder,
        chainMs: Long,
        isSuccessful: Boolean,
        code: Int,
        headers: String,
        segments: List<String>,
        message: String
    ): Runnable {
        return Runnable {
            Printer.printFileResponse(
                builder,
                chainMs,
                isSuccessful,
                code,
                headers,
                segments,
                message
            )
        }
    }

    class Builder {
        internal val headers: HashMap<String, String> = HashMap()
        internal val httpUrl: HashMap<String, String>
        internal var isLogHackEnable = false
            private set
        internal var isDebug: Boolean = false
        internal var type = Platform.INFO
            private set
        internal var requestTag: String = ""
        internal var responseTag: String = ""
        private var level = Level.BASIC
        internal var logger: Logger? = null
            private set
        internal var executor: Executor? = null
            private set
        internal var isMockEnabled: Boolean = false
        internal var sleepMs: Long = 0
        internal var listener: BufferListener? = null

        init {
            httpUrl = HashMap()
        }

        internal fun getLevel(): Level {
            return level
        }

        /**
         * @param level set log level
         * @return Builder
         * @see Level
         */
        fun setLevel(level: Level): Builder {
            this.level = level
            return this
        }

        internal fun getTag(isRequest: Boolean): String {
            return if (isRequest) {
                if (requestTag.isEmpty()) TAG else requestTag
            } else {
                if (responseTag.isEmpty()) TAG else responseTag
            }
        }

        /**
         * @param name Filed
         * @param value Value
         * @return Builder
         * Add a field with the specified value
         */
        fun addHeader(name: String, value: String): Builder {
            headers[name] = value
            return this
        }

        /**
         * @param name Filed
         * @param value Value
         * @return Builder
         * Add a field with the specified value
         */
        fun addQueryParam(name: String, value: String): Builder {
            httpUrl[name] = value
            return this
        }

        /**
         * Set request and response each log tag
         *
         * @param tag general log tag
         * @return Builder
         */
        fun tag(tag: String): Builder {
            TAG = tag
            return this
        }

        /**
         * Set request log tag
         *
         * @param tag request log tag
         * @return Builder
         */
        fun request(tag: String): Builder {
            this.requestTag = tag
            return this
        }

        /**
         * Set response log tag
         *
         * @param tag response log tag
         * @return Builder
         */
        fun response(tag: String): Builder {
            this.responseTag = tag
            return this
        }

        /**
         * @param isDebug set can sending log output
         * @return Builder
         */
        fun loggable(isDebug: Boolean): Builder {
            this.isDebug = isDebug
            return this
        }

        /**
         * @param type set sending log output com.netacom.com.netacom.base.chat.type
         * @return Builder
         * @see Platform
         */
        fun log(type: Int): Builder {
            this.type = type
            return this
        }

        /**
         * @param logger manuel logging interface
         * @return Builder
         * @see Logger
         */
        fun logger(logger: Logger): Builder {
            this.logger = logger
            return this
        }

        /**
         * @param executor manual executor for printing
         * @return Builder
         * @see Logger
         */
        fun executor(executor: Executor): Builder {
            this.executor = executor
            return this
        }

        /**
         * @param useMock let you use json file from asset
         * @param sleep let you see progress dialog when you request
         * @return Builder
         * @see LoggingInterceptor
         */
        fun enableMock(useMock: Boolean, sleep: Long, listener: BufferListener): Builder {
            this.isMockEnabled = useMock
            this.sleepMs = sleep
            this.listener = listener
            return this
        }

        /**
         * Call this if you want to have formatted pretty output in Android Studio logCat.
         * By default this 'hack' is not applied.
         *
         * @param useHack setup builder to use hack for Android Studio v3+ in order to have nice
         * output as it was in previous A.S. versions.
         * @return Builder
         * @see Logger
         */
        fun enableAndroidStudio_v3_LogsHack(useHack: Boolean): Builder {
            isLogHackEnable = useHack
            return this
        }

        fun build(): LoggingInterceptor {
            return LoggingInterceptor(this)
        }

        companion object {

            private var TAG = "ChatSDK"
        }
    }
}
