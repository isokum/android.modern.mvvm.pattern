package net.sokum.mordern.app.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val accessToken : String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().addHeader(
            "X-Api-Key", accessToken).build()

        return chain.proceed(request)
    }
}