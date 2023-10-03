package com.ramseys.jobplan.Composables.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.ramseys.jobplan.Composables.DataClassRep.RecentChat
import com.ramseys.jobplan.Composables.ui.ui.theme.JOBPLANTheme
import com.ramseys.jobplan.Data.Controlleur.ApiClient
import com.ramseys.jobplan.R
import com.ramseys.jobplan.data.Model.UserItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class ChatScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JOBPLANTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val user = intent.getSerializableExtra("user") as? UserItem

                    if (user != null) {
                        Screen(user)
                    } else Toast.makeText(
                        LocalContext.current,
                        "Erreur de chargement de l'utilisateur",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        }
    }
}


@Composable
fun ChatScreen(user: UserItem, modifier: Modifier = Modifier) {

    Box(modifier = Modifier.fillMaxSize()) {
        val conf = LocalConfiguration.current
        val height = conf.screenHeightDp.dp


        val listOfChat = listOf(
            RecentChat("Commandant", R.drawable.valide, "Besoin de vous!", "10:08"),
            RecentChat(
                "Chef de Service",
                R.drawable.asecna_logo,
                "Le planning est disponible!",
                "11:08"
            ),
            RecentChat("Abakar", R.drawable.asecna_logo, "Je suis à l'AIM!", "14:08")
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(10.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(10.dp)
                    .background(Color.White, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .clip(RoundedCornerShape(20.dp)),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                listOfChat.forEach { recentChat ->
                    //ChatWidget()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(thisUser: UserItem) {

    val messages = remember {
        mutableStateListOf<com.ramseys.jobplan.Data.Model.Message>()
    }
    val lifecycleOwner = LocalLifecycleOwner.current

    val apiclient = ApiClient()
    val context = LocalContext.current
    val conf = LocalConfiguration.current
    val height = conf.screenHeightDp.dp

    var isLoading by remember { mutableStateOf(false) }

    Scaffold(

        // Creating Content
        content = {

            // Creating a Column Layout
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(), contentAlignment = Alignment.BottomCenter
            ) {
                Column {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.message),
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .height(240.dp)
                                .fillMaxWidth()
                        )
                    }
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .height(height / 2 + 100.dp)
                            .shadow(2.dp, RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                            .clip(RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        // Creating a Scrollable list of 100 items
                        val lazyListState = rememberLazyListState()
                        Spacer(modifier = Modifier.height(10.dp))
                        Divider(
                            modifier = Modifier
                                .width(40.dp)
                                .height(5.dp)
                                .background(Color.Black)
                        )
                        LazyColumn(
                            Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            lazyListState,
                        ) {
                            isLoading
                            lifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                                while (lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                                    val response = apiclient.getApiService().getRecentChat(thisUser.id)
                                        .awaitResponse()

                                    if (response.isSuccessful) {
                                        val newMessage = response.body() ?: emptyList()

                                        if (newMessage.equals(messages)) {

                                        } else {
                                            messages.clear()
                                            newMessage.forEach { message -> messages.add(message) }
                                        }

                                    }
                                }
                                delay(100)
                            }

                            if (!messages.isEmpty()) {
                                items(messages) {
                                    ChatWidget(chat = it, thisUser)
                                }
                                isLoading = false
                            } else {
                                Toast.makeText(
                                    context,
                                    "Vous n'avez pas de message!!",
                                    Toast.LENGTH_LONG
                                ).show()
                                isLoading =false
                            }

                        }
                    }
                }
                if (isLoading){
                    CircularProgressIndicator()
                }
            }
        }
    )
}

@Composable
fun ChatWidget(chat: com.ramseys.jobplan.Data.Model.Message, user: UserItem) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = null,
            modifier = Modifier
                .width(50.dp)
                .height(50.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .clickable {
                    val intent = Intent(context, Conversation::class.java)
                    intent.putExtra(
                        "receiver",
                        if (chat.idSender == user.id) chat.receiver else chat.sender
                    )
                    intent.putExtra("sender", user.id)
                    startActivity(context, intent, null)

                }
        ) {
            Text(
                text = if (chat.idSender == user.id) "${chat.receiver?.name}" else "${chat.sender?.name}",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(1.dp))
            Row {
                Text(
                    text = "${chat.content}",
                    style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Thin)
                )
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "${chat.created_at}",
                        style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Thin)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Divider(modifier = Modifier.fillMaxWidth())
        }
    }

}
