package com.ramseys.jobplan.Composables.Widget

import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.compose.rememberNavController
import com.ramseys.jobplan.Composables.DataClassRep.DashItem
import com.ramseys.jobplan.Composables.ui.screens.OptionDisplay
import com.ramseys.jobplan.Composables.ui.screens.RegisterPage
import com.ramseys.jobplan.Navigation
import com.ramseys.jobplan.RegisterPage
import com.ramseys.jobplan.data.Model.UserItem


@RequiresApi(value = 24)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun gridView(context: Context, itemList: List<DashItem>, user: UserItem?) {

    val navController = rememberNavController();

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(10.dp)
                .background(Color.Transparent)
        ) {
            itemList.forEachIndexed { index, _ ->
                item {
                    Card(
                        onClick = {

                            if (itemList[index].code == "AA") {
                                val intent = Intent(context, RegisterPage::class.java)
                                startActivity(context, intent, null)
                            } else {
                                Toast.makeText(context, itemList[index].code, Toast.LENGTH_LONG).show()
                                val intent = Intent(context, OptionDisplay::class.java)
                                intent.putExtra("user", user)
                                intent.putExtra("code", itemList[index].code)
                                startActivity(context, intent, null)
                            }

                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .background(Color.Transparent),
                        shape = RoundedCornerShape(20.dp)

                    ) {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = itemList[index].illustration),
                                contentDescription = itemList[index].itemName,
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(100.dp)
                                    .padding(5.dp)
                            )
                            Spacer(modifier = Modifier.height(9.dp))

                            Row(
                                modifier = Modifier
                                    .height(IntrinsicSize.Min)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                            ) {
                                Divider(
                                    color = itemList[index].itemColor,
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(5.dp)
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(30.dp)
                                        .background(color = Color.White),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = itemList[index].itemName,
                                        style = TextStyle(
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                }
                            }

                        }

                    }
                }
            }

        }
    }
}