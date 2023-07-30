package com.ramseys.jobplan.Composables.ui

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.ramseys.jobplan.R


@Composable
fun RegisterPage() {

    val image = painterResource(id = R.drawable.img1);
    val usernameValue = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current;
    val matValue = remember {
        mutableStateOf("")
    }
    val posteValue = remember {
        mutableStateOf("")
    }

    val conf = LocalConfiguration.current;
    val width = conf.screenWidthDp.dp;
    val height = conf.screenHeightDp.dp;
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White), contentAlignment = Alignment.TopCenter){
            Image(painter = image, contentDescription = null,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Blue,
                        shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                    )
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                    )
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .width(width - width / 6)
                .border(width = 2.dp, color = Color.Blue, shape = RoundedCornerShape(30.dp))
                .background(color = Color.White, shape = RoundedCornerShape(30.dp))
                .padding(all = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
           Image(painter = painterResource(id = R.drawable.asecna_logo),
               modifier = Modifier
                   .width(100.dp)
                   .height(100.dp),
               contentDescription = null)
            Text(text = "-Tableau de service horaire-", fontSize = 15.sp, fontWeight = FontWeight.Thin, color = Color.Blue)
            Spacer(modifier = Modifier.padding(10.dp))
            Column( horizontalAlignment = Alignment.CenterHorizontally) {
                OutlinedTextField(
                    value = usernameValue.value,
                    onValueChange = { usernameValue.value = it},
                    label = {
                        Text(text = "Username")
                    },
                    placeholder = {
                        Text(text = "Username")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(
                    value = matValue.value,
                    onValueChange = { matValue.value = it },
                    label = {
                        Text(text = "Matricule")
                    },
                    placeholder = {
                        Text(text = "Matricule")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                OutlinedTextField(
                    value = posteValue.value,
                    onValueChange = { posteValue.value = it },
                    label = { Text(text = "Poste")},
                    placeholder = { Text(text = "Poste")},
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.padding(10.dp))
               Column(
                   horizontalAlignment = Alignment.End,
                   verticalArrangement = Arrangement.Center,
                   modifier = Modifier
                       .fillMaxWidth()
                       .padding(10.dp)) {
                   Button(
                       onClick = {

                           Toast.makeText(context,"Nom:"+usernameValue.value+" Matricule:"+matValue.value, Toast.LENGTH_SHORT).show()
                                 },
                       modifier = Modifier
                           .width(80.dp)
                           .background(
                               color = MaterialTheme.colors.primary,
                               shape = RoundedCornerShape(30.dp)
                           )
                           .border(
                               2.dp,
                               color = MaterialTheme.colors.primary,
                               RoundedCornerShape(30.dp)
                           )
                           .height(50.dp)
                   ) {
                       Text(text = "Next", fontSize = 10.sp)
                       Icon(painter = painterResource(id = R.drawable.next), contentDescription = null , modifier = Modifier.width(60.dp))
                   }
               }
            }

        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPagePreview(){
    RegisterPage()
}