package com.example.ssutudy.util.service


import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(val authToken : String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder().header("Authorization", authToken)

        val request = builder.build()
        return chain.proceed(request)
    }
}