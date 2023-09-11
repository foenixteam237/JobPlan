package com.ramseys.jobplan.Data.Controlleur

import com.ramseys.jobplan.Data.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnexionInterface {
    fun ApiConnexion(): Retrofit{

        val client = OkHttpClient.Builder()
            .hostnameVerifier { _, _ -> true  }.build()

        return Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}