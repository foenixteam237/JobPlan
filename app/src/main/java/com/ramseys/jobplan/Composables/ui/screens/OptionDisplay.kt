package com.ramseys.jobplan.Composables.ui.screens

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ramseys.jobplan.Composables.getWorkplaceUsers
import com.ramseys.jobplan.Composables.ui.AdminDashBoad
import com.ramseys.jobplan.Composables.ui.HomeView
import com.ramseys.jobplan.Composables.ui.ListTableau
import com.ramseys.jobplan.Composables.ui.PlanningSet
import com.ramseys.jobplan.data.Model.UserItem
import com.ramseys.jobplan.data.Model.Workplace
import com.ramseys.jobplan.ui.theme.JOBPLANTheme

class OptionDisplay : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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
                        when(val extra = intent.extras?.getString("code")){
                            "TS"-> ListTableau(user)
                            "DA"-> Greeting(name = extra)
                            "UMIRE"-> Greeting(name = extra)
                            "USLi"-> Greeting(name = extra)
                            "UENM"-> Greeting(name = extra)
                            "GU" ->UserGestUi(user)
                            "LA" -> UserListDisplay(user = user, extra)
                            "VU" -> UserListDisplay(user = user, extra)
                            "SMS" -> Greeting(name = extra)
                            "VT"-> Greeting(name = extra)
                            "PU"-> Profile(user = user)
                            else ->{
                                Toast.makeText(LocalContext.current, extra, Toast.LENGTH_SHORT).show()
                                PlanningSet(userItem = user, poste = extra!!,
                                    getWorkplaceUsers(LocalContext.current, extra)
                                )
                            }
                        }
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
fun Greeting(name: String, modifier: Modifier = Modifier) {


    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    JOBPLANTheme {
        Greeting("Android")
    }
}