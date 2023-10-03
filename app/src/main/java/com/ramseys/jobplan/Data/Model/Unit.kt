package com.ramseys.jobplan.Data.Model

import java.io.Serializable

data class Unit(
    val id: Int,
    val name: String,
    val unitCode: String
): Serializable
