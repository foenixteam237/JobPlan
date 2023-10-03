package com.ramseys.jobplan.Composables.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ramseys.jobplan.Composables.getWorkplaceUsers
import com.ramseys.jobplan.Composables.ui.screens.Profile
import com.ramseys.jobplan.Composables.ui.ui.theme.JOBPLANTheme
import com.ramseys.jobplan.data.Model.UserItem

class UserProfile : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JOBPLANTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val user = intent.getSerializableExtra("user") as UserItem
                    Profile(user)

                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    JOBPLANTheme {
        Greeting2("Android")
    }
}