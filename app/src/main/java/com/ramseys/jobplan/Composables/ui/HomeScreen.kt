package com.ramseys.jobplan.Composables.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.ramseys.jobplan.Composables.Widget.MyCanva
import com.ramseys.jobplan.Composables.ui.screens.OptionDisplay
import com.ramseys.jobplan.Data.Controlleur.ApiClient
import com.ramseys.jobplan.Data.Model.Planning
import com.ramseys.jobplan.Data.Request.WorkPlacesRequest
import com.ramseys.jobplan.Data.Request.WorkplaceResponse
import com.ramseys.jobplan.R
import com.ramseys.jobplan.data.Model.UserItem
import com.ramseys.jobplan.data.Model.Workplace
import com.ramseys.jobplan.ui.theme.JOBPLANTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JOBPLANTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val user = intent.getSerializableExtra("user") as? UserItem

                    if (user != null) {
                        val workplaces: List<Workplace> = user.workplaces
                        HomeView(user, workplaces, user.qualification.name)
                    } else Toast.makeText(
                        LocalContext.current,
                        "Erreur de chargement de l'utilisateur",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}


@Composable
fun HomeView(user: UserItem, workplace: List<Workplace>, poste: String) {
    val conf = LocalConfiguration.current;
    val height = conf.screenHeightDp.dp;
    val width = conf.screenWidthDp.dp;
    val mContext = LocalContext.current
    var apiClient = ApiClient()

    var planning: Planning by remember {
        mutableStateOf(Planning(null, null, null, null, null, null, null, null, null, null, null))
    }

    val option = workplace;

    var mExpanded by remember {
        mutableStateOf(false)
    }

    var wExpanded by remember {
        mutableStateOf(false)
    }

    var selectedOption by remember {
        mutableStateOf(option[0].name)
    }
    var idWorkplace by remember {
        mutableStateOf(0)
    }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    apiClient.getApiService().getWorkplace(WorkPlacesRequest(selectedOption)).enqueue(
        object : Callback<ArrayList<WorkplaceResponse>> {
            override fun onResponse(
                call: Call<ArrayList<WorkplaceResponse>>,
                response: Response<ArrayList<WorkplaceResponse>>
            ) {
                val workplaceResponse = response.body()

                if (response.isSuccessful && workplaceResponse != null) {
                    planning = workplaceResponse[0].planning[0]
                } else {
                    Toast.makeText(
                        mContext,
                        "Error " + response.code() + " " + response.headers() + " " + response.isSuccessful + " " + response.message(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<WorkplaceResponse>>, t: Throwable) {
                Toast.makeText(
                    mContext,
                    "Fail to get planning" + t.cause + " " + t.message,
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        //Background of main activity
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.home_page),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(width)
                    .height(height)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(200.dp)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_account_circle_24),
                    contentDescription = null,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(RoundedCornerShape(30.dp))
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Mr/Mme: ${user.name}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(text = "Postes :$poste", fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }
                Spacer(modifier = Modifier.width(width / 7))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.width(width / 6)
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_chat_bubble_24),
                        contentDescription = null,
                        modifier = Modifier
                            .height(30.dp)
                            .clickable {
                                val intent = Intent(mContext, ChatScreen::class.java)
                                intent.putExtra("user", user)
                                startActivity(mContext, intent, null)
                            },
                        tint = Color.Black
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_more_vert_24),
                        contentDescription = null,
                        modifier = Modifier
                            .height(50.dp)
                            .clickable {
                                mExpanded = !mExpanded
                            },
                        tint = Color.Black
                    )

                    DropdownMenu(
                        expanded = mExpanded,
                        onDismissRequest = { mExpanded = false })
                    {
                        DropdownMenuItem(
                            text = { Text(text = "Profil") },
                            onClick = {
                                val intent = Intent(mContext, OptionDisplay::class.java)
                                intent.putExtra("user", user)
                                intent.putExtra("code", "PU")
                                //startActivity(mContext, intent, null)

                                Toast.makeText(mContext, user.name, Toast.LENGTH_SHORT).show()
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Absence") },
                            onClick = {
                                Toast.makeText(mContext, "Absence", Toast.LENGTH_SHORT).show()
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Déconnecte") },
                            onClick = {
                                Toast.makeText(mContext, "Logout", Toast.LENGTH_SHORT).show()
                            })
                        DropdownMenuItem(
                            text = { Text(text = "Décompte Horaire") },
                            onClick = {
                                Toast.makeText(
                                    mContext,
                                    "Decompte Horaire",
                                    Toast.LENGTH_SHORT
                                ).show()
                            })
                    }


                }

            }
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,

                ) {
                Text(
                    text = "-ASECNA CAMEROUN-\n-AERODROME DE GAROUA-",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row {
                        Text(
                            text = "SERVICE: $selectedOption ",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(icon, contentDescription = null,
                            Modifier
                                .clickable {
                                    wExpanded = !wExpanded
                                }
                                .height(30.dp))

                        DropdownMenu(
                            expanded = wExpanded,
                            onDismissRequest = {
                                wExpanded = false
                            },
                        ) {
                            option.forEach { item ->
                                DropdownMenuItem(text = {
                                    Text(
                                        text = item.name, style = TextStyle(
                                            fontSize = 12.sp
                                        )
                                    )
                                }, onClick = {
                                    selectedOption = item.name
                                    idWorkplace = item.id
                                    wExpanded = false
                                })
                            }
                        }
                    }
                    Text(
                        text = "Status: " + if (planning.status == 1) "Validé" else "Provisoire",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Blue
                    )
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "Année: " + planning.year,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Mois: " + planning.months?.desc,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Semaine: N°" + planning.week,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(10.dp)
                    .shadow(
                        2.dp,
                        shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp),
                        clip = false,
                        ambientColor = Color.Blue,
                        spotColor = Color.Blue
                    )
            ) {


                MyCanva(planning)
                Row {
                    Text(
                        text = "Les couleurs suivantes représentent les heures de travail.\n Le rouge 1: QM, vert: HB, bleu: QS, rouge 2: QS2",
                        style = TextStyle(
                            fontSize = 10.sp,
                            color = Color.Red
                        )
                    )
                }
            }

        }
    }
}

@Composable
private fun loadWorkplaceData(
    apiClient: ApiClient,
    selectedOption: String,
    planning: Planning,
    mContext: Context
): Planning {
    lateinit var planning1: Planning
    apiClient.getApiService().getWorkplace(WorkPlacesRequest(selectedOption)).enqueue(
        object : Callback<ArrayList<WorkplaceResponse>> {
            override fun onResponse(
                call: Call<ArrayList<WorkplaceResponse>>,
                response: Response<ArrayList<WorkplaceResponse>>
            ) {
                val workplaceResponse = response.body()

                if (response.isSuccessful && workplaceResponse != null) {
                    planning1 = workplaceResponse[0].planning[0]
                    Toast.makeText(mContext, "Planning chargé" + planning1.year, Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(
                        mContext,
                        "Error " + response.code() + " " + response.headers() + " " + response.isSuccessful + " " + response.message(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<WorkplaceResponse>>, t: Throwable) {
                Toast.makeText(
                    mContext,
                    "Fail to get planning" + t.cause + " " + t.message,
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    )
    return planning1
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JOBPLANTheme {
        //HomeView("ABAKAR", workplace = )
    }
}