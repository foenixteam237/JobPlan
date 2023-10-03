package com.ramseys.jobplan.Composables.Widget

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ramseys.jobplan.Composables.ui.viewModel.MessageViewModel
import com.ramseys.jobplan.Data.Model.Message
import com.ramseys.jobplan.R


@SuppressLint("UnrememberedMutableState")
@Composable
fun MessageSection(context: Context, idReceiver: Int, idSender:Int) {
    val viewModel: MessageViewModel = MessageViewModel()
    val message = mutableStateOf("")
    Card(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
    ) {

        OutlinedTextField(
            value = message.value,
            onValueChange = {
                message.value = it
            },
            shape = RoundedCornerShape(25.dp),
            trailingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_send_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                            viewModel.sendMessage(
                                Message(
                                    content = message.value,
                                    idReceiver = idReceiver,
                                    isSeen = 0,
                                    created_at = null,
                                    id = null,
                                    idSender = idSender,
                                    sender = null,
                                    receiver = null,
                                    updated_at = null
                                ),
                                context
                            )
                            message.value = ""

                    }
                )
            },
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        )
    }
}