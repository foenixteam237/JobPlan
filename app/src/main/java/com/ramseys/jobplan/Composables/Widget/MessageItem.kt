package com.ramseys.jobplan.Composables.Widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MessageItem(messageText: String?, time: String, isOut: Boolean) {
    val BotChatBubbleShape = RoundedCornerShape(0.dp,8.dp,8.dp,8.dp)
    val AuthorChatBubbleShape = RoundedCornerShape(8.dp,0.dp,8.dp,8.dp)

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isOut) Alignment.End else Alignment.Start
    ) {
        if (messageText != null){
            if (messageText != ""){
                Box(modifier = Modifier
                    .background(
                        if (isOut) MaterialTheme.colorScheme.primary else Color.Blue,
                        shape = if (isOut) AuthorChatBubbleShape else BotChatBubbleShape
                    )
                    .padding(
                        top = 8.dp,
                        bottom = 8.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                ){
                    Text(text = messageText, color = Color.White)
                }
            }
        }
        Text(text = time, fontSize = 12.sp, modifier = Modifier.padding(start = 8.dp) )
    }
}