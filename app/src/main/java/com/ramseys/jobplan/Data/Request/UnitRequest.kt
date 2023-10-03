package com.ramseys.jobplan.Data.Request

import com.google.gson.annotations.SerializedName
import com.ramseys.jobplan.data.Model.Workplace

data class UnitResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("workplaces")
    val workplace:List<Workplace>
)
