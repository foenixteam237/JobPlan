package com.ramseys.jobplan

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramseys.jobplan.Composables.DataClassRep.DashItem
import com.ramseys.jobplan.ui.theme.JOBPLANTheme

class AdminDashBoad : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JOBPLANTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold() {
                        AdminScreen("Android")
                    }

                }
            }
        }
    }
}

@Composable
fun AdminScreen(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current;
    val height = LocalConfiguration.current.screenHeightDp.dp

    Box(modifier = Modifier
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
                        shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
                    ),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.admin_amico),
                    contentDescription = "Aireport")
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height / 2)
            ) {
                gridView(context = context)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    JOBPLANTheme {
        AdminScreen("Android")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun gridView(context: Context){

    lateinit var itemList: List<DashItem>
    itemList = ArrayList<DashItem>()

    itemList = itemList + DashItem("Tableaux de Services", Color.Blue, R.drawable.timeplan)
    itemList = itemList + DashItem("Droit d'accès", Color.Blue, R.drawable.access_right)
    itemList = itemList + DashItem("Gérer utilisateur", Color.Blue, R.drawable.user_flow)
    itemList = itemList + DashItem("Messagerie", Color.Blue, R.drawable.messagerie)


    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Transparent)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier= Modifier
                .padding(10.dp)
                .background(Color.Transparent)
        ) {
            itemList.forEachIndexed {
                    index, _ ->
                item{
                    Card(
                        onClick = {
                            Toast.makeText(context, itemList[index].itemName+" selected", Toast.LENGTH_LONG).show()
                        }
                    ,
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

                            Row(modifier = Modifier
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
                                Box(modifier = Modifier
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