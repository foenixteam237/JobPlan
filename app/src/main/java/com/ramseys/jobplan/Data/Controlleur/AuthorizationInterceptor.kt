package com.ramseys.jobplan.Data.Controlleur

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(val token: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticateRequest = request.newBuilder()
            .header("Authorization", "Bearer "+token)
            .build()

        return  chain.proceed(authenticateRequest)
    }

}