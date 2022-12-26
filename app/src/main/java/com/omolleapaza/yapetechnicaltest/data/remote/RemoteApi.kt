package com.omolleapaza.yapetechnicaltest.data.remote

import okhttp3.OkHttpClient
import okhttp3.Request

private const val URL = ""

class RemoteApi {

    private val httpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    fun buildGetRequest(api: String): Request {
        return Request.Builder().apply {
            get()
            url(URL.plus(api))
        }.build()
    }

    fun callService(request: Request) = httpClient.newCall(request).execute()
}