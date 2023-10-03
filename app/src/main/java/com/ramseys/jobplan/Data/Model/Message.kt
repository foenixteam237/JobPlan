package com.ramseys.jobplan.Data.Model

import com.ramseys.jobplan.data.Model.UserItem

data class Message(
    val content: String?,
    val created_at: String?,
    val id: Int?,
    val idReceiver: Int?,
    val idSender: Int?,
    val isSeen: Int?,
    val sender: UserItem?,
    val receiver: UserItem?,
    val updated_at: String?,
)