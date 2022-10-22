package com.example.nasaapp.base

import android.content.Context
import com.example.nasaapp.R
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.BufferedReader
import java.io.IOException
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class RawResponseInterceptor(private val context: Context) : Interceptor {

    private var mContentType = "application/json"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val uri = chain.request().url.toUri()

        if (uri.path.contains("api/nasa/data")) {
            try {
                val jsonString = context.resources
                    .openRawResource(R.raw.nasa)
                    .bufferedReader()
                    .use(BufferedReader::readText)

                val builder = Response.Builder()
                builder.request(chain.request())
                builder.protocol(Protocol.HTTP_1_0)
                builder.addHeader("content-type", mContentType)

                builder.body(
                    ResponseBody.create(
                        mContentType.toMediaTypeOrNull(),
                        jsonString.toByteArray()
                    )
                )
                builder.code(200)
                builder.message(jsonString.toString())

                return builder.build()
            } catch (e: IOException) {

            }
        }

        return chain.proceed(chain.request())

    }
}