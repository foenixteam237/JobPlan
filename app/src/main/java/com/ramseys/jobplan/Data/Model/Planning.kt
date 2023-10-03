package com.ramseys.jobplan.Data.Model

import java.io.Serializable

data class Planning(
    val by: Int?,
    val created_at: String?,
    val id: Int?,
    val idMonth: Int?,
    val idWorkplaces: Int?,
    var programs: List<Program>?,
    val status: Int?,
    val updated_at: String?,
    val week: Int?,
    val year: Int?,
    val months: Month?

): Serializable