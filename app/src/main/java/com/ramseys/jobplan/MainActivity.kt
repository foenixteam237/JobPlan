package com.ramseys.jobplan

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ramseys.jobplan.Composables.ui.LoginPage
import com.ramseys.jobplan.Composables.ui.RegisterPage
import com.ramseys.jobplan.Composables.ui.RegisterPage2
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            navigation()
        }
    }
}
@Preview
@Composable
fun navigation(){
    val navController = rememberNavController();
    val context = LocalContext.current;
    //val image = (id = R.drawable.img1);
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ){
        composable("splash_screen"){
            SplashScreen(navController = navController)
        }

        composable("login_screen"){
          LoginPage(navController = navController)
        }
        composable("register_screen"){
            RegisterPage(navController = navController)
        }
        composable("register_screen2"){
            RegisterPage2(context=context)
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true){
        delay(5000L)
        navController.navigate("login_screen")


    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Image(painter = painterResource(id = R.drawable.asecna_logo), contentDescription = "",
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
            )
            Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "JOB", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.Blue)
                    Text(text = "PLAN", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.Cyan)
                }
            Text(text = "-Tableau de service Horaire-", fontSize = 14.sp, fontWeight = FontWeight.Thin, color = Color.Blue)
    }
}