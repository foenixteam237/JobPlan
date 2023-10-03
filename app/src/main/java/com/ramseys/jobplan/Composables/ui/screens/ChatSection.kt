package com.ramseys.jobplan.Composables.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramseys.jobplan.Composables.Widget.MessageItem
import com.ramseys.jobplan.Composables.ui.viewModel.MessageViewModel
import com.ramseys.jobplan.data.Model.UserItem
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ChatSection(
    modifier: Modifier,
    viewModel: MessageViewModel,
    userItem: UserItem
) {
    val simpleDataFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH)
    LazyColumn(
        modifier =
        modifier
            .fillMaxSize()
            .padding(16.dp),
        reverseLayout = true
    ){

        items(viewModel.message){
            chat->MessageItem(
            messageText = chat.content,
            time = chat.created_at.toString(),
            isOut = chat.idSender != userItem.id,
        )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
