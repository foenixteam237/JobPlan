package com.ramseys.jobplan.Data.Request

import com.google.gson.annotations.SerializedName
import com.ramseys.jobplan.data.Model.UserItem

data class LoginRequest(
    @SerializedName("registrationNumber")
    val matricule: String,

    @SerializedName("password")
    val password :String
)

data class LoginResponse(

    @SerializedName("status")
    var status : Int,

    @SerializedName("token")
    var authToken : String,

    @SerializedName("user")
    var user : UserItem
)