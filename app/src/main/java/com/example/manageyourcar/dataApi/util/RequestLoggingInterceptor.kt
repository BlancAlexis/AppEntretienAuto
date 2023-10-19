package com.example.manageyourcar.dataApi.util

import okhttp3.Interceptor
import okhttp3.Response


class RequestLoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url()
        val method = request.method()

        println("Request URL: $url")
        println("Request Method: $method")

        return chain.proceed(request)
    }
}