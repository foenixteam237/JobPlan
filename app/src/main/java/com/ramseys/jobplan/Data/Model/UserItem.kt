package com.ramseys.jobplan.data.Model

import com.ramseys.jobplan.Data.Model.Unit
import java.io.Serializable


data class UserItem(
    val birthDate: String,
    val birthPlace: String,
    val created_at: String,
    val fisrtName: String,
    val id: Int,
    val idNationality: Int,
    val idQualification: Int,
    val idRole: Int,
    val marialStatus: String,
    var name: String,
    val nationality: Nationality,
    val qualification: Qualification,
    val units: Unit,
    val recruitmentDate: String,
    val registrationNumber: String,
    val role: Role,
    val sex: String,
    val updated_at: String,
    val workplaces: List<Workplace>,
    val status: Int,
    val unit_id: Int,
): Serializable

