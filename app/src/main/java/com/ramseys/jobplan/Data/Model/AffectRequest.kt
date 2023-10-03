package com.ramseys.jobplan.Data.Model

import com.google.gson.annotations.SerializedName

data class AffectRequest(
    @SerializedName("idWorkplace")
    val work: Int,
    @SerializedName("idUser")
    val idUser: Int
)

data class AffectResponse(
    @SerializedName("idWorkplace")
    val status: Int,
)

