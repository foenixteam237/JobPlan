package com.ramseys.jobplan.Composables.ui.screens

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.ramseys.jobplan.Composables.Widget.diminutif
import com.ramseys.jobplan.Composables.Widget.userItem
import com.ramseys.jobplan.Data.Controlleur.ApiClient
import com.ramseys.jobplan.Data.Model.AffectRequest
import com.ramseys.jobplan.Data.Model.AffectResponse
import com.ramseys.jobplan.Data.Request.UnitResponse
import com.ramseys.jobplan.Data.Request.WorkplaceResponse
import com.ramseys.jobplan.Data.Request.statusResponse
import com.ramseys.jobplan.R
import com.ramseys.jobplan.data.Model.UserItem
import com.ramseys.jobplan.data.Model.Workplace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse


@Composable
fun Profile(user: UserItem) {
    val height = LocalConfiguration.current.screenHeightDp.dp
    val width = LocalConfiguration.current.screenWidthDp.dp
    Column {
        Box(
            modifier = Modifier
                .width(width)
                .height((height / 3)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_interface),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                Modifier
                    .width(width / 3)
                    .height(width / 3)
                    .background(
                        Color.Transparent,
                        shape = RoundedCornerShape(50.dp)
                    )
                    .border(2.dp, Color.Cyan, shape = RoundedCornerShape(width / 3)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = null,
                    Modifier
                        .clip(RoundedCornerShape(width / 3))
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = diminutif(user.name + if (user.fisrtName == null) "" else " " + user.fisrtName),
                    style = TextStyle(fontSize = 40.sp, fontWeight = FontWeight.Bold)
                )
            }
        }
        Column(
            modifier = Modifier
                .width(width)
                .fillMaxHeight()
                .shadow(
                    3.dp,
                    RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    true,
                    Color.Transparent,
                    Color.Blue
                )
                .background(
                    Color.Transparent,
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                )
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            information(width, user)
        }
    }
}

@Composable
fun information(width: Dp, user: UserItem) {
    val context = LocalContext.current
    val usernameValue = remember {
        mutableStateOf(user.name + if (user.fisrtName == null) "" else " " + user.fisrtName)
    }
    val unitValue = remember {
        mutableStateOf(user.units.name)
    }
    val qualifValue = remember {
        mutableStateOf(user.qualification.name)
    }
    val recruitementDate = remember {
        mutableStateOf(user.recruitmentDate)
    }
    var showSheet by remember { mutableStateOf(false) }
    if (showSheet) {
        BottomSheet(user, context) {
            showSheet = false
        }
    }
    var isLoading by remember { mutableStateOf(false) }

    var isValide by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(10.dp))
            Divider(
                Modifier
                    .width(if (width <= 300.dp) width / 3 else 100.dp)
                    .height(2.dp)
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Information Personnelle",
                Modifier.fillMaxWidth(),
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = usernameValue.value,
                    onValueChange = { usernameValue.value = it },
                    label = {
                        Text(text = "Nom")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = unitValue.value,
                    onValueChange = { unitValue.value = it },
                    label = {
                        Text(text = "Unité")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                OutlinedTextField(
                    value = qualifValue.value,
                    onValueChange = { qualifValue.value = it },
                    label = {
                        Text(text = "Qualification")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )
                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = recruitementDate.value,
                    onValueChange = { recruitementDate.value = it },
                    label = {
                        Text(text = "Date recrutement")
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                Spacer(modifier = Modifier.height(5.dp))
                OutlinedTextField(
                    value = user.registrationNumber,
                    onValueChange = { recruitementDate.value = it },
                    label = {
                        Text(text = "Matricule")
                    },
                    readOnly = true,
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.8f)
                )

                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    isValide = (user.status == 0)
                    if (isValide) {
                        Button(
                            onClick = {

                                isLoading = true
                                ApiClient().getApiService().statusChange(user.registrationNumber)
                                    .enqueue(
                                        object : Callback<statusResponse> {
                                            override fun onResponse(
                                                call: Call<statusResponse>,
                                                response: Response<statusResponse>
                                            ) {
                                                if (response.isSuccessful && response.code() == 200) {
                                                    Toast.makeText(
                                                        context,
                                                        "Utilisateur ${user.registrationNumber} a été approuvé",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                    isLoading = false
                                                    showSheet = true
                                                    isValide = true
                                                }
                                            }

                                            override fun onFailure(
                                                call: Call<statusResponse>,
                                                t: Throwable
                                            ) {
                                                isLoading = false
                                                Toast.makeText(
                                                    context,
                                                    t.message,
                                                    Toast.LENGTH_SHORT
                                                )
                                                    .show()
                                            }

                                        }
                                    )
                            },
                            enabled = !isLoading
                        ) {
                            Text(text = "Valider", style = TextStyle(fontSize = 12.sp))
                        }

                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Button(onClick = {
                        Toast.makeText(context, "Modifier", Toast.LENGTH_SHORT).show()
                    }) {
                        Text(text = "Modifier", style = TextStyle(fontSize = 12.sp))
                    }
                }
            }


        }
        if (isLoading) {
            CircularProgressIndicator()
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(user: UserItem, context: Context, onDismiss: () -> Unit) {


    val lifecycleOwner = LocalLifecycleOwner.current
    val apiClient = ApiClient()
    val modalBottomSheetState = rememberModalBottomSheetState()

    var workplaList = remember {
        mutableStateListOf<Workplace>()
    }
    var selectedWorkplaList = remember {
        mutableStateListOf<Workplace>()
    }
    var width = LocalConfiguration.current.screenWidthDp.dp
    val height = LocalConfiguration.current.screenHeightDp.dp - 100.dp
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        modifier = Modifier.height(height)
    ) {
        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "Affecter un poste") }) },
            modifier = Modifier.fillMaxSize(),
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.poste),
                        contentDescription = null,
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .width(width)
                            .height(height / 2 - 50.dp)
                            .padding(10.dp)
                    ) {
                        Column(
                            Modifier.width(width / 2 - 10.dp)
                        ) {
                            val lazyListState = rememberLazyListState()
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.White),
                                state = lazyListState
                            ) {
                                apiClient.getApiService().getWorkplaceList(user.unit_id).enqueue(
                                    object : Callback<UnitResponse> {
                                        override fun onResponse(
                                            call: Call<UnitResponse>,
                                            response: Response<UnitResponse>
                                        ) {
                                            if (response.isSuccessful) {
                                                workplaList.clear()
                                                workplaList.addAll(response.body()?.workplace!!)
                                            } else {
                                                Toast.makeText(
                                                    context,
                                                    "Erreur de chargement des postes",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                        }

                                        override fun onFailure(
                                            call: Call<UnitResponse>,
                                            t: Throwable
                                        ) {
                                            Toast.makeText(
                                                context,
                                                "Erreur " + t.message,
                                                Toast.LENGTH_LONG
                                            ).show()
                                        }

                                    }
                                )
                                items(workplaList) { workplace ->
                                    Row(
                                        Modifier
                                            .fillMaxSize()
                                            .padding(5.dp)
                                            .background(
                                                Color.White,
                                                shape = RoundedCornerShape(50.dp)
                                            ),

                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        Text(text = "${workplace.name}", Modifier.clickable {
                                            if (selectedWorkplaList.isEmpty()) {
                                                if (user.workplaces.contains(workplace)){
                                                    Toast.makeText(context, "L'utilisateur est déja affecté à ce poste", Toast.LENGTH_LONG).show()
                                                }else{
                                                    selectedWorkplaList.add(workplace)
                                                }
                                            } else {
                                                if (selectedWorkplaList.contains(workplace)) {
                                                    Toast.makeText(
                                                        context,
                                                        "Déjà selectionné",
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                } else {
                                                    if (user.workplaces.contains(workplace)){
                                                        Toast.makeText(context, "L'utilisateur est déja affecté à ce poste", Toast.LENGTH_LONG).show()
                                                    }else{
                                                        selectedWorkplaList.add(workplace)
                                                    }
                                                }
                                            }
                                        }, style = TextStyle(fontSize = 12.sp))
                                    }
                                }
                            }

                        }
                        Divider(
                            Modifier
                                .width(5.dp)
                                .fillMaxHeight()
                                .background(Color.Transparent)
                        )
                        Column(
                            Modifier.width(width / 2 - 10.dp),
                            verticalArrangement = Arrangement.spacedBy(2.dp),
                            horizontalAlignment = Alignment.End
                        ) {
                            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End) {
                                LazyColumn(
                                    Modifier
                                        .fillMaxSize()
                                        .background(Color.White),
                                    verticalArrangement = Arrangement.spacedBy(2.dp),
                                    horizontalAlignment = Alignment.End

                                ) {
                                    items(selectedWorkplaList) { workplaList ->
                                        Row(
                                            Modifier
                                                .fillMaxSize()
                                                .padding(5.dp)
                                                .background(
                                                    Color.White,
                                                    shape = RoundedCornerShape(50.dp)
                                                ),

                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Start

                                        ) {
                                            Text(
                                                text = "Poste ${workplaList.name}",
                                                Modifier.clickable {
                                                    selectedWorkplaList.remove(workplaList)
                                                },
                                                style = TextStyle(fontSize = 12.sp)
                                            )
                                        }
                                        Divider(Modifier.width(width / 5))
                                    }
                                }
                            }
                        }
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .width(width - 100.dp)
                            .padding(10.dp)
                    ) {
                        Button(onClick = {
                            selectedWorkplaList.forEach { workplace ->
                                run {
                                    apiClient.getApiService().affectWorkplace(AffectRequest(workplace.id, user.id))
                                        .enqueue(
                                            object : Callback<AffectResponse> {
                                                override fun onResponse(
                                                    call: Call<AffectResponse>,
                                                    response: Response<AffectResponse>
                                                ) {

                                                    if (response.isSuccessful) {
                                                        Toast.makeText(
                                                            context,
                                                            "Affecté!",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    } else {
                                                        Toast.makeText(
                                                            context,
                                                            "Erreur d'affectation"+ response.body(),
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                    }
                                                }

                                                override fun onFailure(
                                                    call: Call<AffectResponse>,
                                                    t: Throwable
                                                ) {
                                                    Toast.makeText(
                                                        context,
                                                        t.message,
                                                        Toast.LENGTH_LONG
                                                    ).show()
                                                }

                                            }
                                        )
                                }
                            }

                        }) {
                            Text(text = "Affecter", style = TextStyle(fontSize = 10.sp))
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(onClick = {

                        }) {
                            Text(text = "Annuler!", style = TextStyle(fontSize = 10.sp))
                        }
                    }

                }

            }
        )
    }
}

