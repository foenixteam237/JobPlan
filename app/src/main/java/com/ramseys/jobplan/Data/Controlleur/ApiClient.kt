package com.ramseys.jobplan.Data.Controlleur

import android.content.Context
import com.ramseys.jobplan.Data.RetrofitAPI
import okhttp3.OkHttpClient

class ApiClient {

    private lateinit var apiService: RetrofitAPI

    fun getApiService(): RetrofitAPI{

        if (!::apiService.isInitialized){
            val retro = ApiConnexionInterface().ApiConnexion()
            apiService = retro.create(RetrofitAPI::class.java)
        }

        return apiService
    }

    private  fun okhttpClient(context: Context): OkHttpClient{

        return  OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor(context))
            .build()
    }
}