package com.ramseys.jobplan

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramseys.jobplan.ui.theme.JOBPLANTheme
import com.ramseys.jobplan.ui.theme.primaryColor

class HomeScreen : ComponentActivity() {
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
                    HomeView("ABAKAR")
                }
            }
        }
    }
}

@Composable
fun HomeView(name: String, modifier: Modifier = Modifier) {
    val conf = LocalConfiguration.current;
    val height = conf.screenHeightDp.dp;
    val width = conf.screenWidthDp.dp;
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
    //Background of main activity
        Box( 
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(painter = painterResource(id = R.drawable.home_page), contentDescription =null, contentScale = ContentScale.Crop )
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
                Icon(painter = painterResource(id = R.drawable.baseline_account_circle_24),
                    contentDescription = null,
                    modifier = Modifier
                        .width(50.dp)
                        .height(50.dp)
                        .clip(RoundedCornerShape(30.dp))
                    )
                Spacer(modifier = Modifier.padding(5.dp))
                Column( horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center) {
                    Text(
                        text = "Mr/Mme: $name",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(text = "Postes", fontSize = 12.sp, fontWeight = FontWeight.Thin)
                }
                Spacer(modifier = Modifier.width(width/4))
                Column( horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Center) {

                   Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.End){
                       Icon(painter = painterResource(id = R.drawable.baseline_chat_bubble_24),
                           contentDescription = null,
                           modifier = Modifier
                               .height(30.dp),
                           tint = Color.Black
                       )
                       Spacer(modifier = Modifier.padding(5.dp))
                       Icon(painter = painterResource(id = R.drawable.baseline_more_vert_24), contentDescription = null, modifier = Modifier.height(30.dp), tint = Color.Black)

                   }

               }

           }
            Spacer(modifier = Modifier.padding(20.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,

            ){
                Text(text = "-ASECNA CAMEROUN AERODROME DE GAROUA-", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(modifier = Modifier.height(20.dp))
                Column( verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
                    Text(text = "SERVICE ENM", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Status:.................", fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color.Blue)
                }
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
                Text(text = "Année:", fontWeight = FontWeight.Bold)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JOBPLANTheme {
        HomeView("ABAKAR")
    }
}