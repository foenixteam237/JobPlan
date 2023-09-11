package com.ramseys.jobplan.Data

import com.ramseys.jobplan.Data.Request.LoginRequest
import com.ramseys.jobplan.Data.Request.LoginResponse
import com.ramseys.jobplan.data.Model.UserItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitAPI {

    @GET("users")
    fun
            getUsers(): Call<ArrayList<UserItem>>

    @POST(Constantes.LOGIN_URL)
    fun
            login(@Body request: LoginRequest): Call<LoginResponse>

}