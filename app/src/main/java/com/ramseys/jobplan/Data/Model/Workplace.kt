package com.ramseys.jobplan.data.Model

import com.ramseys.jobplan.Data.Model.Planning
import java.io.Serializable

data class Workplace(
    val idUnit: Int,
    val name: String,
    val pivot: Pivot,
    val desc: String,
    val id: Int,
    val plannings: List<Planning>,
    val users: List<UserItem>
): Serializable