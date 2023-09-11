package com.ramseys.jobplan.Data.Controlleur

import com.ramseys.jobplan.Data.RetrofitAPI

class ApiClient {

    private lateinit var apiService: RetrofitAPI

    fun getApiService(): RetrofitAPI{

        if (!::apiService.isInitialized){
            val retro = ApiConnexionInterface().ApiConnexion()
            apiService = retro.create(RetrofitAPI::class.java)
        }

        return apiService
    }
}