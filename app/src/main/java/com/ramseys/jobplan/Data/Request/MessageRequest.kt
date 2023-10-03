package com.ramseys.jobplan.Data.Request

import com.google.gson.annotations.SerializedName

data class MessageRequest(
    @SerializedName("idReceiver")
    val idReceiver:Int,
    @SerializedName("idSender")
    val idSender:Int,
    @SerializedName("content")
    val content: String,
)