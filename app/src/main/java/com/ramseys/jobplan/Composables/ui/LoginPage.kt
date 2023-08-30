package com.ramseys.jobplan.Composables.ui

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.ramseys.jobplan.AdminDashBoad
import com.ramseys.jobplan.HomeScreen
import com.ramseys.jobplan.RegisterPage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavController){
    val image = painterResource(id = com.ramseys.jobplan.R.drawable.image)
    val matValue = remember{ mutableStateOf("") }
    val context = LocalContext.current
    val passwordValue = remember {
        mutableStateOf("")
    }
    val conf = LocalConfiguration.current
    val passwordVisibility = remember {
        mutableStateOf(false)
    }
    val width = conf.screenWidthDp.dp
    val height = conf.screenHeightDp.dp
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center,){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
            contentAlignment = Alignment.TopCenter,

            ) {
            Image(painter = image,
                contentDescription = null,
                modifier = Modifier.width(width).height(height),
                contentScale = ContentScale.Crop)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .width(width - width / 6)
                .height(height - (height - 450.dp))
                .border(width = 2.dp, color = Color.Blue, shape = RoundedCornerShape(30.dp))
                .background(Color.White, shape = RoundedCornerShape(30.dp))
                .padding(all = 10.dp),


            ) {
            //Text(text = "Sing In", style = TextStyle(fontWeight = FontWeight.Bold, letterSpacing = TextUnit.Companion.Unspecified), fontSize = 30.sp)
            Image(painter = painterResource(id = com.ramseys.jobplan.R.drawable.asecna_logo), modifier = Modifier
                .width(100.dp)
                .height(100.dp), contentDescription =null )
            Text(text = "-Tableau de service Horaire-", fontSize = 15.sp, fontWeight = FontWeight.Thin, color = Color.Blue)
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(value = matValue.value, onValueChange = {matValue.value = it},
                    label = { Text(text = "Matricule") },
                    placeholder = { Text(text = "Matricule") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f))

                OutlinedTextField(
                    value = passwordValue.value,
                    onValueChange ={passwordValue.value = it},
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }) {
                            Icon(painter = painterResource(id = com.ramseys.jobplan.R.drawable.ic_baseline_remove_red_eye_24), contentDescription =null,
                                tint = if (passwordVisibility.value) Color.Blue else Color.Gray )
                        }
                    },
                    label = { Text(text = "Mot de passe") },
                    placeholder = { Text(text = "Mot de passe") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                Spacer(modifier = Modifier.padding(10.dp))
                Button(onClick = {



                    if (!(matValue.value == "2022SR" && passwordValue.value == "1234")){
                        val intent = Intent(context, HomeScreen::class.java)
                        startActivity(context, intent, null)

                    }else{
                        val intent = Intent(context, AdminDashBoad::class.java)
                        startActivity(context, intent, null)
                        Toast.makeText(context, "Bienvenue Monsieur", Toast.LENGTH_LONG).show()
                    }

                             },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .border(
                            2.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .height(50.dp)) {
                    Text(text = "Se connecter", fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.padding(10.dp))
                Text(text = "Créer un nouveau compte", modifier = Modifier
                    .padding(all = 10.dp)
                    .clickable {
                        Toast.makeText(context,"OK",Toast.LENGTH_LONG).show()
                        val intent = Intent(context, RegisterPage::class.java)
                        startActivity(context, intent,null )
                    }, color = Color.Blue)
                Spacer(modifier = Modifier.padding(10.dp))
            }

        }

    }

}

@Preview
@Composable
fun LoginPreview(){
    //LoginPage()
}