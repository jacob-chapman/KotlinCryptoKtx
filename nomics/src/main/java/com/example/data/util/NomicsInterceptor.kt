package com.example.data.util

import okhttp3.Interceptor
import okhttp3.Response

class NomicsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain
                    .request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("key", "0df45dc611aeb0f9c4ce533d76fbb201")
                    .build()

        val request = chain.request().newBuilder().url(url).build()
        return chain.proceed(request)
    }

}