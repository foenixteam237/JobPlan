package com.ramseys.jobplan.Data.Request

import com.google.gson.annotations.SerializedName
import com.ramseys.jobplan.Data.Model.Planning

data class WorkPlacesRequest(
    @SerializedName("name")
    val name:String,
)

data class WorkplaceResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("plannings")
    val planning: ArrayList<Planning>
)

