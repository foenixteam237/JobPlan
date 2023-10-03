package com.ramseys.jobplan.Composables.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.ramseys.jobplan.Composables.Widget.MessageSection
import com.ramseys.jobplan.Composables.Widget.TopBarSection
import com.ramseys.jobplan.Composables.ui.screens.ChatSection
import com.ramseys.jobplan.Composables.ui.screens.ui.theme.JOBPLANTheme
import com.ramseys.jobplan.Composables.ui.viewModel.MessageViewModel
import com.ramseys.jobplan.R
import com.ramseys.jobplan.data.Model.UserItem
import com.ramseys.jobplan.data.Model.Workplace

class Conversation : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JOBPLANTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val user = intent.getSerializableExtra("receiver") as? UserItem
                    val meUser = intent.getIntExtra("sender", 0)
                    if (user != null) {
                        Conversation(userItem = user, meUser = meUser )
                    } else Toast.makeText(
                        LocalContext.current,
                        "Erreur de chargement de l'utilisateur",
                        Toast.LENGTH_LONG
                    ).show()
                    //Conversation()
                }
            }
        }
    }
}

@Composable
fun Conversation(viewModel: MessageViewModel = MessageViewModel(), userItem: UserItem, meUser: Int) {

    val context = LocalContext.current

    viewModel.refresh(meUser, userItem.id, context)
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        TopBarSection(
            userName = userItem.name,
            profile = painterResource(id = R.drawable.profile_interface),
            isOline = true
        )
        ChatSection(
            modifier = Modifier
                .weight(1f)
                .background(Color.White), viewModel = viewModel, userItem = userItem
        )
        MessageSection(context = context, idReceiver = userItem.id, meUser)
    }
}



