package com.ramseys.jobplan.Composables.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramseys.jobplan.Composables.DataClassRep.DashItem
import com.ramseys.jobplan.Composables.Widget.gridView
import com.ramseys.jobplan.R
import com.ramseys.jobplan.data.Model.UserItem

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun UserGestUi(user: UserItem) {

    lateinit var itemList: List<DashItem>
    itemList = ArrayList<DashItem>()

    itemList = itemList + DashItem(
        "File d'attente", Color.Blue, R.drawable.profile, "VU"
    )
    itemList = itemList + DashItem(
        "Poste", Color.Blue, R.drawable.poste, "AP"
    )
    itemList = itemList + DashItem(
        "Liste Agent", Color.Blue, R.drawable.enroulement, "LA"
    )
    itemList = itemList + DashItem(
        "Ajouter un Agent", Color.Blue, R.drawable.add_user, "AA"
    )

    val height = LocalConfiguration.current.screenHeightDp.dp
    val width = LocalConfiguration.current.screenWidthDp.dp
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    ){
        Column(modifier = Modifier
            .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .width(width)
                    .height((height / 2) - 20.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.user_flow), contentDescription = null, Modifier.width(width) )
            }
            Column(
                modifier = Modifier
                    .width(width)
                    .height((height / 2) +20.dp)
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
                    .padding(2.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                gridView(context = context , itemList = itemList , user = user)
            }
        }
    }

}