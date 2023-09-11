package com.ramseys.jobplan.Composables.ui

import android.content.Context
import android.icu.util.Calendar
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.ramseys.jobplan.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(navController: NavController, context: Context) {

    val image = painterResource(id = R.drawable.profile_interface);
    val usernameValue = remember {
        mutableStateOf("")
    }
    val matValue = remember {
        mutableStateOf("")
    }


    var posteValue by remember {
        mutableStateOf("")
    }
    val postList = listOf(
        "IRE",
        "IGC",
        "METEO",
        "IRE",
        "IGC",
        "METEO",
        "IRE",
        "IGC",
        "METEO",
        "IRE",
        "IGC",
        "METEO",
        "IRE",
        "IGC",
        "METEO",
        "IRE",
        "IGC",
        "METEO", "IRE", "IGC", "METEO", "IRE", "IGC", "METEO", "IRE", "IGC", "METEO"
    )

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    var mExpanded by remember { mutableStateOf(false) }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    val conf = LocalConfiguration.current;
    val width = conf.screenWidthDp.dp;
    val height = conf.screenHeightDp.dp;
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White), contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = image, contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                    .width(width)

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
            Image(
                painter = painterResource(id = R.drawable.asecna_logo),
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp),
                contentDescription = null
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
                    value = usernameValue.value,
                    onValueChange = { usernameValue.value = it },
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
                    value = posteValue,
                    onValueChange = { posteValue = it },
                    label = { Text(text = "Poste") },
                    //placeholder = { Text(text = "Poste")},
                    //singleLine = true,

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
                                        text = "Choisisez votre poste",
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
                                    items(postList.size) { post ->
                                        Text(
                                            text = postList[post],
                                            modifier = Modifier.clickable {
                                                posteValue = postList[post]
                                                mExpanded = false
                                            })
                                    }
                                }
                            }

                        }
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Column(
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Button(
                        onClick = {
                            navController.navigate("register_screen2")
                            Toast.makeText(context, "ok", Toast.LENGTH_LONG).show()
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
                        Text(text = "Next", fontSize = 10.sp)
                        Icon(
                            painter = painterResource(id = R.drawable.next),
                            contentDescription = null,
                            modifier = Modifier.width(60.dp)
                        )
                    }
                }
            }

        }

    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun MyUI() {

    val calendar =  Calendar.getInstance()
    calendar.set(1990, 0, 22)


}