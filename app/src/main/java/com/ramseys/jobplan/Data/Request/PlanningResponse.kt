package com.ramseys.jobplan.Data.Request

import com.google.gson.annotations.SerializedName
import com.ramseys.jobplan.data.Model.UserItem

data class PlanningResponse(
    @SerializedName("statut")
    val status: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("message")
    val message: String
)

data class ProgramResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String
)

data class ProgramResquest(
    @SerializedName("idDay")
    var idDay: Int,
    @SerializedName("idHour")
    val idHour: Int,
    @SerializedName("idPlanning")
    var idPlanning: Int,
    @SerializedName("user_id")
    val user_id: Int
)