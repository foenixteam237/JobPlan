package com.ramseys.jobplan.Composables.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.ramseys.jobplan.Composables.Widget.userItem
import com.ramseys.jobplan.Data.Controlleur.ApiClient
import com.ramseys.jobplan.R
import com.ramseys.jobplan.data.Model.UserItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse
import kotlin.collections.ArrayList

@SuppressLint("CoroutineCreationDuringComposition")
@Composable

fun UserListDisplay(user: UserItem, code: String) {

    val width = LocalConfiguration.current.screenWidthDp.dp
    val height = LocalConfiguration.current.screenHeightDp.dp
    var search = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    var usersList = remember {
        mutableStateListOf<UserItem>()
    }

    var isLoading by remember { mutableStateOf(true) }
    var show by remember {
        mutableStateOf(false)
    }
    val apiClient = ApiClient()
    val lifecycleOwner = LocalLifecycleOwner.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .width(width)
                    .height((height / 3)),
                contentAlignment = Alignment.BottomEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user_flow),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {

                    OutlinedTextField(
                        value = search.value,
                        onValueChange = { search.value = it },
                        label = {
                            Text(
                                text = "Nom Agent",
                                style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Thin)
                            )
                        },
                        placeholder = {
                            Text(text = "Nom agent", style = TextStyle(fontSize = 10.sp))
                        },
                        singleLine = true,
                        modifier = Modifier
                            .width(width / 2)
                            .fillMaxHeight(6f)
                    )
                    IconButton(
                        onClick = {

                            Toast.makeText(context, "Recherche", Toast.LENGTH_LONG).show()
                        },
                        Modifier
                            .fillMaxHeight(8f)
                            .width(50.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_search_24),
                            contentDescription = null,
                        )
                    }
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
                val lazyListState = rememberLazyListState()

                apiClient.getApiService().getUsers()
                    .enqueue(object : Callback<ArrayList<UserItem>> {
                        override fun onResponse(
                            call: Call<ArrayList<UserItem>>,
                            response: Response<ArrayList<UserItem>>
                        ) {
                            val uList = response.body()
                            if (response.isSuccessful) {
                                if (code == "LA") {
                                    usersList.clear()
                                    if (uList != null) {
                                        usersList.addAll(uList)
                                        isLoading = false
                                    }
                                } else {
                                    usersList.clear()
                                    uList?.forEach { user ->
                                        run {
                                            val vaUser = ArrayList<UserItem>()
                                            if (user.status == 0) {
                                                vaUser.add(user)
                                            }

                                            if (vaUser.size<0){
                                                Toast.makeText(context, "Pas de nouvel utilisateur",Toast.LENGTH_LONG).show()
                                                isLoading = false
                                            }else{
                                                usersList.addAll(vaUser)
                                                isLoading = false
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        override fun onFailure(call: Call<ArrayList<UserItem>>, t: Throwable) {
                            Toast.makeText(context, "Erreur de chargemenr "+t.message, Toast.LENGTH_SHORT).show()
                            isLoading = false
                        }

                    })

                if (!isLoading) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White),
                        state = lazyListState
                    ) {

                        items(usersList) { user ->
                            userItem(user = user)
                        }
                    }
                } else {
                    CircularProgressIndicator()
                }
            }
        }

    }

}

@Composable
private fun showToast(
    context: Context,
) {
    Toast.makeText(
        context,
        "Erreur de chargement de la liste ",
        Toast.LENGTH_LONG
    ).show()
}