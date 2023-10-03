package com.ramseys.jobplan.Composables.ui.screens

import android.content.Context
import android.icu.util.Calendar
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
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
import androidx.compose.ui.platform.LocalContext
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
import androidx.navigation.NavController
import com.google.gson.GsonBuilder
import com.ramseys.jobplan.Composables.DataClassRep.UserData
import com.ramseys.jobplan.Composables.getUnitData
import com.ramseys.jobplan.Data.Model.Unit
import com.ramseys.jobplan.R
import com.ramseys.jobplan.data.Model.UserItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(navController: NavController, context: Context) {
    lateinit var user: UserItem
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

     var idUnit by remember {
         mutableStateOf(0)
     }

    val unitList = remember { mutableStateListOf<Unit>() }
    unitList.clear()
    getUnitData(unitList, context)

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    var mExpanded by remember { mutableStateOf(false) }
    // var mOpenPage by remember { mutableStateOf(false) }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    var dateValue by remember {
        mutableStateOf("")
    }

    val calendar = Calendar.getInstance()
    calendar.set(1990, 0, 22)

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = calendar.timeInMillis)
    DatePicker(state = datePickerState)

    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.ROOT)

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    var selectedDate by remember {
        mutableLongStateOf(calendar.timeInMillis)
    }

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
                    label = { Text(text = "Unité") },

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
                OutlinedTextField(
                    value = dateValue,
                    onValueChange = { dateValue = it },
                    label = { Text(text = "Date recrutement") },

                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .onGloballyPositioned { coordinates ->
                            mTextFieldSize = coordinates.size.toSize()
                        },
                    readOnly = true,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_date_range_24),
                            contentDescription = null,
                            Modifier.clickable { showDatePicker = !showDatePicker },
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                )
                if (showDatePicker) {
                    DatePickerDialog(
                        modifier = Modifier.width(width - width / 6),
                        onDismissRequest = {
                            showDatePicker = false
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                showDatePicker = false
                                selectedDate = datePickerState.selectedDateMillis!!
                            }) {
                                Text(text = "Confirm")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = {
                                showDatePicker = false

                            }) {
                                Text(text = "Cancel")
                            }
                        }
                    ) {
                        DatePicker(state = datePickerState)

                    }

                }
                dateValue = formatter.format(Date(selectedDate))
                if (mExpanded) {
                    Dialog(
                        onDismissRequest = { mExpanded = false }
                    ) {
                        Surface(
                            shape = MaterialTheme.shapes.extraLarge,
                            modifier = Modifier
                                .width(width / 2)
                                .height(height / 4)
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
                                        text = "Choisisez votre unité",
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
                                    items(unitList) { unit ->
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                text = unit.unitCode,
                                                style = TextStyle(
                                                    fontSize = 15.sp,
                                                    fontWeight = FontWeight.ExtraLight,

                                                    ),
                                                modifier = Modifier.clickable {
                                                    posteValue = unit.unitCode
                                                    idUnit = unit.id
                                                    mExpanded = false
                                                })
                                        }
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

                    val user = UserData(usernameValue.value, matValue.value, idUnit, dateValue)

                    Button(
                        onClick = {
                            if(usernameValue.value.isEmpty() || matValue.value.isEmpty() || posteValue.isEmpty()){
                                Toast.makeText(context,"Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                            } else{
                                val gson = GsonBuilder().create()
                                val userJson = gson.toJson(user)

                                navController.navigate(
                                    "register_screen2/{user}".replace(
                                        oldValue = "{user}",
                                        newValue = userJson
                                    )
                                )

                                Toast.makeText(context, "ok "+user, Toast.LENGTH_LONG).show()
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


    var qualiValue by remember {
        mutableStateOf("")
    }


    val qualificationList = listOf(
        "Cadre IRE",
        "Cadre IGC"
    )

    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }

    var mExpanded by remember { mutableStateOf(false) }

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
                    label = { Text(text = "Unité") },
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
                                        text = "Choisisez votre unité",
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
                                    items(qualificationList.size) { post ->
                                        Text(
                                            text = qualificationList[post],
                                            modifier = Modifier.clickable {
                                                qualiValue = qualificationList[post]
                                                mExpanded = false
                                            })
                                    }
                                }
                            }

                        }
                    }
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
                        ContextCompat.startActivity(context, intent, null)*/
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


@Composable
fun displayListView() {
    val context = LocalContext.current
    val unit = remember { mutableStateListOf<Unit>() }

    getUnitData(unit, context)

    LazyColumn {
        items(unit) { user ->
            Row {
                Column {
                    Text(text = user.name, style = TextStyle(fontSize = 17.sp))

                }
            }
        }
    }
}