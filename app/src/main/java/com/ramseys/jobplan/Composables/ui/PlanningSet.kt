package com.ramseys.jobplan.Composables.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramseys.jobplan.Composables.Widget.MyCanva
import com.ramseys.jobplan.Composables.getWorkplaceUsers
import com.ramseys.jobplan.Composables.ui.theme.JOBPLANTheme
import com.ramseys.jobplan.Data.Controlleur.ApiClient
import com.ramseys.jobplan.Data.Model.Day
import com.ramseys.jobplan.Data.Model.Hour
import com.ramseys.jobplan.Data.Model.Planning
import com.ramseys.jobplan.Data.Model.Program
import com.ramseys.jobplan.Data.Request.PlanningResponse
import com.ramseys.jobplan.Data.Request.ProgramResponse
import com.ramseys.jobplan.Data.Request.ProgramResquest
import com.ramseys.jobplan.R
import com.ramseys.jobplan.data.Model.UserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

class PlanningSet : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JOBPLANTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    val user = intent.getSerializableExtra("user") as UserItem
                    val poste = intent.extras?.getString("poste")
                    if (user != null && poste != null) {
                        PlanningSet(user, poste, getWorkplaceUsers(LocalContext.current, poste))
                    }

                }
            }
        }
    }
}

@Composable
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
fun PlanningSet(userItem: UserItem, poste: String, userList: MutableList<UserItem>) {
    val usersList: MutableList<UserItem> = userList
    val width = LocalConfiguration.current.screenWidthDp.dp
    val height = LocalConfiguration.current.screenHeightDp.dp
    val context = LocalContext.current


    val hour = arrayOf(
        Hour(1, "HB"),
        Hour(2, "QM"),
        Hour(3, "QS"),
        Hour(4, "QM2")
    )
    val day = arrayOf(
        Day(1, "Lundi"),
        Day(2, "Mardi"),
        Day(3, "Mercredi"),
        Day(4, "Jeudi"),
        Day(5, "Vendredi"),
        Day(6, "Samedi"),
        Day(7, "Dimanche")
    )

    var expanded by remember {
        mutableStateOf(false)
    }
    var stateChange by remember {
        mutableStateOf(false)
    }
    var hExpanded by remember {
        mutableStateOf(false)
    }

    var dExpanded by remember {
        mutableStateOf(false)
    }

    var selectedName by remember { mutableStateOf("") }
    var selectedHour by remember { mutableStateOf(hour[0].codeHour) }
    var selectedDay by remember { mutableStateOf(day[0].desc) }


    var selectedUser: UserItem? by remember {
        mutableStateOf(usersList.getOrNull(0))
    }

    //program information
    var idUser by remember {
        mutableStateOf(0)
    }
    var idDay by remember {
        mutableStateOf(0)
    }
    var idHour by remember {
        mutableStateOf(0)
    }

    var planning: Planning by remember {
        mutableStateOf(
            Planning(
                userItem.id,
                null,
                0,
                LocalDate.now().monthValue,
                5,
                null,
                null,
                null,
                getNumeroSemaine(LocalDate.now().toString()),
                LocalDate.now().year,
                null
            )
        )
    }

    var programs: MutableList<Program> = remember {
        mutableStateListOf<Program>()
    }

    var showSheet by remember { mutableStateOf(false) }

    if (showSheet) {
        BottomSheet(planning, context) {
            showSheet = false
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height / 2 + 20.dp)
                    .shadow(
                        3.dp,
                        RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp),
                        true,
                        Color.Transparent,
                        Color.Blue
                    )
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Planification des Tableau de Service",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.asecna_logo),
                        contentDescription = null,
                        Modifier
                            .width(80.dp)
                            .height(80.dp)
                    )
                    Text(
                        text = "***Agence pour la Sécurisation de la Navigation Aérienne en Afrique et en Madagascar***",
                        style = TextStyle(
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Text(
                            text = "Poste de travail: $poste",
                            style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(
                            text = "Nombre d'Agent: ${userList.size}",
                            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        Text(
                            text = "Planning N°:", style = TextStyle(
                                fontWeight = FontWeight.Bold, fontSize = 14.sp
                            )
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Par:M/Mme ${userItem.name}",
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )
                    }

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height / 5 + 20.dp)
                        .padding(10.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        ExposedDropdownMenuBox(expanded = expanded,
                            onExpandedChange = { expanded = !expanded }) {
                            TextField(
                                value = selectedName,
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = "Nom de l'agent",
                                        style = TextStyle(fontSize = 10.sp)
                                    )
                                },
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = expanded
                                    )
                                },
                                modifier = Modifier
                                    .menuAnchor()
                                    .width(width / 2 + 10.dp)
                                    .height(height / 12 - 10.dp),
                                textStyle = TextStyle(
                                    fontSize = 10.sp
                                )
                            )
                            ExposedDropdownMenu(expanded = expanded,
                                onDismissRequest = { expanded = false }) {
                                usersList.forEach { user ->
                                    DropdownMenuItem(text = { Text(text = user.name + " " + user.registrationNumber) },
                                        onClick = {
                                            selectedName = user.name
                                            selectedUser = user
                                            idUser = user.id
                                            expanded = false
                                        })
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        ExposedDropdownMenuBox(expanded = hExpanded,
                            onExpandedChange = { hExpanded = !hExpanded }) {
                            TextField(
                                value = selectedHour,
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = "Heure",
                                        style = TextStyle(fontSize = 10.sp)
                                    )
                                },
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = hExpanded
                                    )
                                },
                                modifier = Modifier.menuAnchor()
                            )
                            ExposedDropdownMenu(expanded = hExpanded,
                                onDismissRequest = { hExpanded = false }) {
                                hour.forEach { name ->
                                    DropdownMenuItem(text = { Text(text = name.codeHour) },
                                        onClick = {
                                            selectedHour = name.codeHour
                                            idHour = name.id
                                            hExpanded = false
                                        })
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        ExposedDropdownMenuBox(
                            expanded = dExpanded,
                            onExpandedChange = { dExpanded = !dExpanded },

                            ) {
                            TextField(
                                value = selectedDay,
                                onValueChange = {},
                                label = {
                                    Text(
                                        text = "Jour",
                                        style = TextStyle(fontSize = 10.sp)
                                    )
                                },
                                readOnly = true,
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                        expanded = dExpanded
                                    )
                                },
                                modifier = Modifier
                                    .menuAnchor()
                                    .width(width / 3 + 10.dp)
                                    .height(height / 12 - 10.dp)
                            )
                            ExposedDropdownMenu(expanded = dExpanded,
                                onDismissRequest = { dExpanded = false }) {
                                day.forEach { name ->
                                    DropdownMenuItem(text = { Text(text = name.desc) }, onClick = {
                                        selectedDay = name.desc
                                        idDay = name.id
                                        dExpanded = false
                                    })
                                }
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(
                            onClick = {
                                var program = Program(1, idDay, idHour, 0, selectedUser!!, idUser)
                                var isNoExist = false
                                if (programs.isEmpty()) {
                                    programs.add(program)
                                } else {
                                    programs.forEach { prog ->
                                        if (prog.idDay == program.idDay && prog.idHour == program.idHour && prog.user == program.user) {
                                            Toast.makeText(
                                                context,
                                                "Utilisateur déjà programmé en ce jour à la meme heure",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                            isNoExist = false

                                        } else isNoExist = true
                                    }
                                    if (isNoExist) {
                                        programs.add(program)
                                    }
                                }

                                planning.programs = programs
                                stateChange = !stateChange
                            }) {
                            Text(text = "Ajouter",style = TextStyle( fontSize = 10.sp))
                        }
                        Button(onClick = {
                            showSheet = true
                        }) {
                            Text(text = "Soumettre", style = TextStyle( fontSize = 10.sp))
                        }
                    }
                }

            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height / 2)
                    .background(Color.Transparent)
            ) {
                //here we set our preview table
                MyCanva(planning = planning)

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(planning: Planning, context: Context, onDismiss: () -> Unit) {
    var apiClient = ApiClient()
    val modalBottomSheetState = rememberModalBottomSheetState()

    var width = LocalConfiguration.current.screenWidthDp.dp
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        modifier = Modifier.height((LocalConfiguration.current.screenHeightDp.dp / 2) - 50.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.valide),
                contentDescription = null,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(width - 50.dp)
                    .padding(10.dp)
            ) {
                Text(text = "Etes vous sure de vouloir soumettre ce tableau de service?")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .width(width - 100.dp)
                    .padding(10.dp)
            ) {
                Button(onClick = {

                    apiClient.getApiService().setPlanning(planning).enqueue(
                        object : Callback<PlanningResponse> {
                            override fun onResponse(
                                call: Call<PlanningResponse>,
                                response: Response<PlanningResponse>
                            ) {
                                val planningResp = response.body()

                                if (response.isSuccessful && response.code() == 200) {
                                    planning.programs?.forEach { program ->
                                        run {
                                            if (planningResp != null) {
                                                program.idPlanning = planningResp.id
                                            }
                                            apiClient.getApiService().setProgram(program)
                                                .enqueue(
                                                    object : Callback<ProgramResponse> {
                                                        override fun onResponse(
                                                            call: Call<ProgramResponse>,
                                                            response: Response<ProgramResponse>
                                                        ) {
                                                            val res = response.body()
                                                            if (response.isSuccessful && res?.status == 201) {
                                                                Toast.makeText(
                                                                    context,
                                                                    "Planning crée avec succès merci d'attendre la validation",
                                                                    Toast.LENGTH_SHORT
                                                                ).show()
                                                            } else {
                                                                Toast.makeText(
                                                                    context,
                                                                    response.message() + " "
                                                                            + response.code() + " "
                                                                            + response.errorBody()
                                                                            + " " + response.body() +
                                                                            " " + response.raw() + "\n" + program.toString(),
                                                                    Toast.LENGTH_LONG
                                                                ).show()
                                                            }
                                                        }

                                                        override fun onFailure(
                                                            call: Call<ProgramResponse>,
                                                            t: Throwable
                                                        ) {
                                                            Toast.makeText(
                                                                context,
                                                                t.message,
                                                                Toast.LENGTH_SHORT
                                                            ).show()
                                                        }

                                                    }
                                                )
                                        }
                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        response.message() + " "
                                                + response.code() + " "
                                                + response.errorBody()
                                                + " " + response.body() +
                                                " " + response.raw() + "\n" + planning.toString(),
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }

                            override fun onFailure(call: Call<PlanningResponse>, t: Throwable) {
                                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                            }

                        }
                    )
                }) {
                    Text(text = "Soumettre", style = TextStyle(fontSize = 10.sp))
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = {

                }) {
                    Text(text = "Annuler!", style = TextStyle(fontSize = 10.sp))
                }
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun getNumeroSemaine(date: String): Int {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val localDate = LocalDate.parse(date, formatter)
    val calendar = Calendar.getInstance(Locale.ROOT)
    calendar.firstDayOfWeek = Calendar.MONDAY
    calendar.minimalDaysInFirstWeek = 4
    calendar.time = Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC))
    return calendar.get(Calendar.WEEK_OF_YEAR)
}


