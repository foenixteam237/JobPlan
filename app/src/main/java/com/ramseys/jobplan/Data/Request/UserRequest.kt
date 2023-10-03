package com.ramseys.jobplan.Data.Request

import com.google.gson.annotations.SerializedName
import com.ramseys.jobplan.data.Model.UserItem

//data class UserRequest()

data class statusResponse(
    @SerializedName("user")
    val user:UserItem,

    )

