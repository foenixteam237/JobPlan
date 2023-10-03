package com.ramseys.jobplan.Composables.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp

import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog
import com.ramseys.jobplan.Composables.DataClassRep.UserData
import com.ramseys.jobplan.Composables.getQualificationData
import com.ramseys.jobplan.Composables.getJsonNationality
import com.ramseys.jobplan.Data.Controlleur.ApiClient
import com.ramseys.jobplan.Data.Request.RegisterRequest
import com.ramseys.jobplan.Data.Request.RegisterResponse
import com.ramseys.jobplan.R
import com.ramseys.jobplan.data.Model.Nationality
import com.ramseys.jobplan.data.Model.Qualification
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun RegisterPage2(
    context: Context,
    user: UserData,
) {
    var apiClient =  ApiClient()

    val passwordValue = remember {
        mutableStateOf("")
    }
    val passVerifValue = remember {
        mutableStateOf("")
    }
    val passwordVisibility = remember {
        mutableStateOf(false)
    }

    var idQaulif by remember {
        mutableStateOf(0)
    }
    var idNationality by remember {
        mutableStateOf(0)
    }

    val qualif = remember { mutableStateListOf<Qualification>() }
    qualif.clear()
    getQualificationData(qualif, context)

    val nationality = remember {
        mutableStateListOf<Nationality>()
    }
    nationality.clear()
    getJsonNationality(nationality, context)

    var nationalityValue by remember {
        mutableStateOf("")
    }
    var qualiValue by remember {
        mutableStateOf("")
    }


    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    var mExpanded by remember { mutableStateOf(false) }
    var nExpanded by remember {
        mutableStateOf(false)
    }
    var progressBar by remember {
        mutableStateOf(false)
    }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val conf = LocalConfiguration.current;
    val width = conf.screenWidthDp.dp;
    val height = conf.screenHeightDp.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp), contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.aircraft_cuate),
                contentDescription = null,
                Modifier
                    .width(width)
                    .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .width(width - width / 6)
                .border(width = 2.dp, color = Color.Blue, shape = RoundedCornerShape(30.dp))
                .background(color = Color.White, shape = RoundedCornerShape(30.dp))
                .padding(10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.asecna_logo),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
            Text(
                text = "-Tableau de service horaire-",
                fontSize = 15.sp,
                fontWeight = FontWeight.Thin,
                color = Color.Blue
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = qualiValue,
                    onValueChange = { qualiValue = it },
                    label = { Text(text = "Qualification") },

                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .onGloballyPositioned { coordinates ->
                            mTextFieldSize = coordinates.size.toSize()
                        },
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            icon,
                            contentDescription = null,
                            Modifier.clickable { mExpanded = !mExpanded })
                    }
                )
                if (mExpanded) {
                    Dialog(

                        onDismissRequest = { mExpanded = false }
                    ) {

                        Surface(
                            shape = MaterialTheme.shapes.extraLarge,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(MaterialTheme.colorScheme.primary)
                                        .shadow(
                                            2.dp,
                                            MaterialTheme.shapes.small,
                                            true,
                                            MaterialTheme.colorScheme.primary
                                        )
                                        .height(50.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Choisisez votre Qualification",
                                        style = TextStyle(
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Spacer(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .background(Color.Black)
                                )
                                LazyColumn(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth()
                                        .height(height / 3),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(10.dp),
                                ) {
                                    items(qualif) { qualification ->
                                        Text(
                                            text = qualification.name,
                                            style = TextStyle(fontSize = 17.sp),
                                            modifier = Modifier.clickable {
                                                qualiValue = qualification.name
                                                idQaulif = qualification.id
                                                mExpanded = false
                                            })
                                    }
                                }
                            }

                        }
                    }
                }
                OutlinedTextField(
                    value = nationalityValue,
                    onValueChange = { nationalityValue = it },
                    label = { Text(text = "Nationalité") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .onGloballyPositioned { coordinates ->
                            mTextFieldSize = coordinates.size.toSize()
                        },
                    readOnly = true,
                    trailingIcon = {
                        Icon(icon, contentDescription = null, Modifier.clickable {
                            nExpanded = !nExpanded
                        })
                    }
                )
                if (nExpanded) {
                    Dialog(
                        onDismissRequest = { nExpanded = false }
                    ) {

                        Surface(
                            shape = MaterialTheme.shapes.extraLarge,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(MaterialTheme.colorScheme.primary)
                                        .shadow(
                                            2.dp,
                                            MaterialTheme.shapes.small,
                                            true,
                                            MaterialTheme.colorScheme.primary
                                        )
                                        .height(50.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "Choisisez votre Nationalité",
                                        style = TextStyle(
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Spacer(
                                    modifier = Modifier
                                        .width(2.dp)
                                        .background(Color.Black)
                                )
                                LazyColumn(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth()
                                        .height(height / 3),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(10.dp),
                                ) {
                                    items(nationality) { nationalite ->
                                        Text(
                                            text = nationalite.nationalite,
                                            style = TextStyle(fontSize = 17.sp),
                                            modifier = Modifier.clickable {
                                                nationalityValue = nationalite.nationalite
                                                idNationality = nationalite.id
                                                nExpanded = false
                                            })
                                    }
                                }
                            }

                        }
                    }
                }
                OutlinedTextField(
                    value = passwordValue.value,
                    onValueChange = { passwordValue.value = it },
                    label = {
                        Text(text = "Password")
                    },
                    placeholder = {
                        Text(text = "Password")
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisibility.value = !passwordVisibility.value
                            }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_remove_red_eye_24),
                                contentDescription = null,
                                tint = if (passwordVisibility.value) Color.Blue else Color.Gray
                            )
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(
                    value = passVerifValue.value,
                    onValueChange = { passVerifValue.value = it },
                    label = {
                        Text(text = "Confirme password")
                    },
                    placeholder = {
                        Text(text = "Confirme password")
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisibility.value = !passwordVisibility.value
                            }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_remove_red_eye_24),
                                contentDescription = null,
                                tint = if (passwordVisibility.value) Color.Blue else Color.Gray
                            )
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {
                        /*val intent = Intent(context, HomeScreen::class.java)
                        startActivity(context, intent, null)*/
                        if (idNationality == 0 || idQaulif == 0 && passwordValue.value.isEmpty()) {
                            Toast.makeText(
                                context,
                                "Veuillez remplir tous les champs",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {

                            if (passwordValue.value.length <= 7) {
                                Toast.makeText(
                                    context,
                                    "Votre mot de passe n'est pas assez long",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                val registerRequest = RegisterRequest(
                                    name = user.name,
                                    fisrtName = "",
                                    password = passwordValue.value,
                                    dateRecruitment = user.dateRecruitement,
                                    registration = user.matricule,
                                    qualif = idQaulif,
                                    idNationality = idNationality,
                                    unit = user.Idunite,
                                    sex = "M",
                                )

                                apiClient.getApiService().register(
                                    registerRequest
                                ).enqueue(object : Callback<RegisterResponse> {
                                    override fun onResponse(
                                        call: Call<RegisterResponse>,
                                        response: Response<RegisterResponse>
                                    ) {
                                        val registerResponse = response.body()

                                        if(registerResponse?.statut == true && response.body() != null){
                                            Toast.makeText(context, "Utilisateur a été créé avec succès. Veuillez vous rapprochez de votre chef d'unité pour la validation de votre compte. MERCI", Toast.LENGTH_LONG).show()
                                        }else {
                                            Toast.makeText(
                                                context,
                                                "Erreur " + response.message() + " " + registerRequest,
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                    }

                                    override fun onFailure(
                                        call: Call<RegisterResponse>,
                                        t: Throwable
                                    ) {
                                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                                    }
                                })
                            }
                        }

                    },
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(30.dp)
                        )
                        .border(
                            2.dp,
                            color = MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(30.dp)
                        )
                        .height(50.dp)
                ) {

                    Text(text = "Sign up", fontSize = 10.sp)
                }
            }
        }

    }
}

