package com.ramseys.jobplan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ramseys.jobplan.Composables.ui.LoginPage
import com.ramseys.jobplan.Composables.ui.RegisterPage
import com.ramseys.jobplan.Composables.ui.RegisterPage2
import com.ramseys.jobplan.Composables.ui.theme.JOBPLANTheme

class RegisterPage : ComponentActivity() {
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

@Composable
fun Navigation(){
    val navController = rememberNavController();
    val context = LocalContext.current;
    //val image = (id = R.drawable.img1);
    NavHost(
        navController = navController,
        startDestination = "register_screen"
    ){
        composable("register_screen"){
            RegisterPage(navController = navController, context)
        }
        composable("register_screen2"){
            RegisterPage2(context=context)
        }
    }
}
