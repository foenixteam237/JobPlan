package com.ramseys.jobplan.Composables.ui.screens

import android.app.Activity
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.ramseys.jobplan.Composables.ui.AdminDashBoad
import com.ramseys.jobplan.Composables.ui.HomeScreen
import com.ramseys.jobplan.Data.Controlleur.ApiClient
import com.ramseys.jobplan.Data.Controlleur.SessionManager
import com.ramseys.jobplan.Data.Request.LoginRequest
import com.ramseys.jobplan.Data.Request.LoginResponse
import com.ramseys.jobplan.RegisterPage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavController) {

    var apiClient = ApiClient()
    var sessionManager = SessionManager(LocalContext.current)

    val image = painterResource(id = com.ramseys.jobplan.R.drawable.image)
    val matValue = remember { mutableStateOf("") }
    val context = LocalContext.current
    val passwordValue = remember {
        mutableStateOf("")
    }
    val conf = LocalConfiguration.current

    var isLoading by remember { mutableStateOf(false) }
    val passwordVisibility = remember {
        mutableStateOf(false)
    }
    val width = conf.screenWidthDp.dp
    val height = conf.screenHeightDp.dp
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.TopCenter,

            ) {
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .width(width)
                    .height(height),
                contentScale = ContentScale.Crop
            )
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
            Image(
                painter = painterResource(id = com.ramseys.jobplan.R.drawable.asecna_logo),
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                contentDescription = null
            )
            Text(
                text = "-Tableau de service Horaire-",
                fontSize = 15.sp,
                fontWeight = FontWeight.Thin,
                color = Color.Blue
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = matValue.value, onValueChange = { matValue.value = it },
                    label = { Text(text = "Matricule") },
                    placeholder = { Text(text = "Matricule") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f),
                    readOnly = isLoading
                )

                OutlinedTextField(
                    value = passwordValue.value,
                    onValueChange = { passwordValue.value = it },
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility.value = !passwordVisibility.value
                        }) {
                            Icon(
                                painter = painterResource(id = com.ramseys.jobplan.R.drawable.ic_baseline_remove_red_eye_24),
                                contentDescription = null,
                                tint = if (passwordVisibility.value) Color.Blue else Color.Gray
                            )
                        }
                    },
                    label = { Text(text = "Mot de passe") },
                    placeholder = { Text(text = "Mot de passe") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(0.8f),
                    readOnly = isLoading
                )

                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {
                        isLoading = true
                        if (!matValue.value.isEmpty() && !passwordValue.value.isEmpty()) {
                            val intent1 = Intent(context, AdminDashBoad::class.java)
                            val intent = Intent(context, HomeScreen::class.java)

                            if (passwordValue.value.length > 7) {
                                apiClient.getApiService().login(
                                    LoginRequest(
                                        matricule = matValue.value,
                                        password = passwordValue.value
                                    )
                                )
                                    .enqueue(object : Callback<LoginResponse> {
                                        override fun onFailure(
                                            call: Call<LoginResponse>,
                                            t: Throwable
                                        ) {
                                            isLoading = false
                                            Toast.makeText(
                                                context,
                                                "Une erreur s'est produit cause: " + t.message,
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }

                                        override fun onResponse(
                                            call: Call<LoginResponse>,
                                            response: Response<LoginResponse>
                                        ) {
                                            val loginResponse = response.body()

                                            if (loginResponse?.status == 201 && loginResponse?.user != null) {
                                                isLoading = false
                                                if (loginResponse.user.status == 0) {
                                                    Toast.makeText(
                                                        context,
                                                        "Votre compte n'a pas été activé veuillez vous rapprochez de votre chef d'unité " + loginResponse.user.status+" "+loginResponse.user.registrationNumber,
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                } else {

                                                    if (loginResponse.user.role.roleName == "Agent") {
                                                        Toast.makeText(
                                                            context,
                                                            "Connecté!",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                        val user = loginResponse?.user
                                                        intent.putExtra("user", user)
                                                        startActivity(context, intent, null)


                                                    } else {
                                                        Toast.makeText(
                                                            context,
                                                            "Connecté! \n Bienvenue!",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                        val user = loginResponse?.user
                                                        intent1.putExtra("user", user)
                                                        startActivity(context, intent1, null)
                                                    }
                                                }
                                            } else {
                                                isLoading = false
                                                Toast.makeText(
                                                    context,
                                                    "Matricule ou mot de passe incorrect",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    })
                            } else {
                                isLoading = false
                                Toast.makeText(
                                    context,
                                    "La longueur du mot de passe doit etre superieur à 7",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            isLoading = false
                            Toast.makeText(
                                context,
                                "Veuillez renseigner les champs",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    },
                    enabled = !isLoading,
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
                        .height(50.dp)
                ) {
                    Text(text = "Se connecter", fontSize = 20.sp)
                }

                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "Créer un nouveau compte", modifier = Modifier
                        .padding(all = 10.dp)
                        .clickable {
                            Toast
                                .makeText(context, "OK", Toast.LENGTH_LONG)
                                .show()
                            val intent = Intent(context, RegisterPage::class.java)
                            startActivity(context, intent, null)
                        }, color = Color.Blue
                )
                Spacer(modifier = Modifier.padding(10.dp))
            }

        }
        if (isLoading) {
            CircularProgressIndicator()
        }
    }

}