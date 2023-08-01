package com.ramseys.jobplan.Composables.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramseys.jobplan.R


@Composable
fun RegisterPage2() {
    val phoneNumber = remember {
        mutableStateOf("")
    }
    val passwordValue = remember {
        mutableStateOf("")
    }
    val passVerifValue = remember {
        mutableStateOf("")
    }
    val passwordVisibility = remember {
        mutableStateOf(false)
    }

    val  conf = LocalConfiguration.current;
    val width = conf.screenWidthDp.dp;
    
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
                    Image(painter= painterResource(id = R.drawable.img1) , contentDescription = null,Modifier.clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)))
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
            Image(painter = painterResource(id = R.drawable.asecna_logo), contentDescription = null, modifier = Modifier
                .width(100.dp)
                .height(100.dp))
            Text(text = "-Tableau de service horaire-", fontSize = 15.sp, fontWeight = FontWeight.Thin, color = Color.Blue)
            Spacer(modifier  = Modifier.padding(10.dp))
            Column( horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = phoneNumber.value,
                    onValueChange = { phoneNumber.value = it },
                    label = { Text(text = "Phone number") },
                    placeholder = { Text(text = "Phone number") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    modifier = Modifier.fillMaxWidth(0.8f),
                )
                OutlinedTextField(value = passwordValue.value,
                    onValueChange = {passwordValue.value = it},
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
                            Icon(painter = painterResource(id = R.drawable.ic_baseline_remove_red_eye_24),
                                contentDescription =null,
                                tint = if (passwordVisibility.value) Color.Blue else Color.Gray)
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier =  Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(value = passVerifValue.value,
                    onValueChange = {passVerifValue.value = it},
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
                            Icon(painter = painterResource(id = R.drawable.ic_baseline_remove_red_eye_24),
                                contentDescription =null,
                                tint = if (passwordVisibility.value) Color.Blue else Color.Gray)
                        }
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordVisibility.value) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier =  Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Button(onClick = { /*TODO*/ }, 
                    modifier = Modifier
                        .width(80.dp)
                        .background(color = MaterialTheme.colors.primary)
                        .clip(RoundedCornerShape(30.dp))) {
                    
                    Text(text = "Sign up", fontSize = 10.sp)
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview(){
    RegisterPage2()
}