package com.ramseys.jobplan.Composables.ui

import android.widget.GridView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

@Composable
@Preview
fun ListTableau(){
    val conf = LocalConfiguration.current
    val height = conf.screenHeightDp.dp
    val width = conf.screenWidthDp.dp

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height / 2)
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
                Image(
                    painter = painterResource(id = R.drawable.timeplan),
                    contentDescription = "Time plan"
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height / 2)
            ) {
                lateinit var itemList: List<DashItem>
                itemList = ArrayList<DashItem>()

                itemList = itemList + DashItem("Unité MIRE/IGC", Color.Blue, R.drawable.main, "MIRE")
                itemList = itemList + DashItem("Unité SLI", Color.Blue, R.drawable.firefighter_amico, "SLI")
                itemList = itemList + DashItem("Unité ENM", Color.Blue, R.drawable.weather_rafiki, "ENM")
                gridView(context = context, itemList = itemList)
            }
        }
    }

}