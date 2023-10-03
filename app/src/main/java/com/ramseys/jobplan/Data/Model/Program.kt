package com.ramseys.jobplan.Data.Model

import com.ramseys.jobplan.data.Model.UserItem
import java.io.Serializable

data class Program(
    val id: Int,
    var idDay: Int,
    val idHour: Int,
    var idPlanning: Int,
    val user: UserItem,
    val user_id: Int
):Serializable