package com.ramseys.jobplan

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ramseys.jobplan.Composables.DataClassRep.UserData
import com.ramseys.jobplan.Composables.ui.screens.RegisterPage
import com.ramseys.jobplan.Composables.ui.screens.RegisterPage2
import com.ramseys.jobplan.Composables.ui.theme.JOBPLANTheme

class RegisterPage : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JOBPLANTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   Navigation()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun Navigation(){
    val navController = rememberNavController();
    val context = LocalContext.current;
    NavHost(
        navController = navController,
        startDestination = "register_screen"
    ){
        composable("register_screen"){
            RegisterPage(navController = navController, context)
        }
        composable("register_screen2/{user}"){
                navBackStackEntry ->
            /* Extracting the id from the route */
            val gson: Gson = GsonBuilder().create()

            val userJson = navBackStackEntry.arguments?.getString("user")

            //convert to json
            val user = gson.fromJson(userJson, UserData::class.java)
            RegisterPage2(context = context, user = user )
        }
    }
}
