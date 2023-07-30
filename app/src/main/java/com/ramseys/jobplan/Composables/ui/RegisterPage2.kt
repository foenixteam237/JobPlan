package com.ramseys.jobplan.Composables.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
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
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview(){
    RegisterPage2()
}