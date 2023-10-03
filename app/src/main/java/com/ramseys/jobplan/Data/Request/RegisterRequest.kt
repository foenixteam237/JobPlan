package com.ramseys.jobplan.Data.Request

import com.google.gson.annotations.SerializedName
import com.ramseys.jobplan.data.Model.UserItem

data class RegisterRequest(
    @SerializedName("name")
    val name: String,

    @SerializedName("firstName")
    val fisrtName: String?,

    @SerializedName("registrationNumber")
    val registration: String,

    @SerializedName("unit")
    val unit:Int,

    @SerializedName("recruitmentDate")
    val dateRecruitment: String,

    @SerializedName("qualification")
    val qualif: Int,

    @SerializedName("password")
    val password: String,

    @SerializedName("nationality")
    val idNationality: Int,

    @SerializedName("sex")
    val sex: String,

)


data class RegisterResponse(
    @SerializedName("status")
    val statut: Boolean,

    @SerializedName("user")
    val userItem: UserItem
)

data class qualification(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val qualif: String
)

data class units(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String
)