package com.ramseys.jobplan.Data.Controlleur

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(context: Context): Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()

        sessionManager.fetchAuthToken()?.let {
            request.addHeader("Authorization", "Bearer + $it")
        }
        return  chain.proceed(request.build())
    }

}