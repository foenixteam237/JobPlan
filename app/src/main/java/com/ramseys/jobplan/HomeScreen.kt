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
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import android.graphics.Paint
import android.widget.Toast
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.IconButton
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramseys.jobplan.ui.theme.JOBPLANTheme

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
    val mContext = LocalContext.current



    var mExpanded by remember {
        mutableStateOf(false)
    }

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
            Image(painter = painterResource(id = R.drawable.home_page), contentDescription =null, contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(width)
                .height(height))
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
                    Text(text = "Postes", fontSize = 12.sp, fontWeight = FontWeight.Medium)
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
                       IconButton(onClick = { 
                           mExpanded = !mExpanded
                       }) {
                           Icon(
                               painter = painterResource(id = R.drawable.baseline_more_vert_24),
                               contentDescription = null,
                               modifier = Modifier.height(30.dp),
                               tint = Color.Black)
                       }
                       DropdownMenu(expanded = mExpanded,
                           onDismissRequest = { mExpanded = false })
                       {
                           DropdownMenuItem( text = { Text(text = "Profil")},onClick = { Toast.makeText(mContext, "Profil", Toast.LENGTH_SHORT).show() })
                           DropdownMenuItem( text = { Text(text = "Absence")},onClick = { Toast.makeText(mContext, "Absence", Toast.LENGTH_SHORT).show() })
                           DropdownMenuItem( text = { Text(text = "Déconnecte")},onClick = { Toast.makeText(mContext, "Logout", Toast.LENGTH_SHORT).show() })
                           DropdownMenuItem( text = { Text(text = "Décompte Horaire")},onClick = { Toast.makeText(mContext, "Decompte Horaire", Toast.LENGTH_SHORT).show() })
                       }
                      

                   }

               }

           }
            Spacer(modifier = Modifier.padding(20.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,

            ){
                Text(text = "-ASECNA CAMEROUN-\n-AERODROME DE GAROUA-", textAlign = TextAlign.Center, fontSize = 20.sp,modifier = Modifier.fillMaxWidth(), fontWeight = FontWeight.ExtraBold)
                Spacer(modifier = Modifier.height(20.dp))
                Column( verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
                    Text(text = "SERVICE ENM", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Status:.................", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Blue)
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
                Text(text = "Année:2023", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = "Mois: Aout", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = "Semaine: Du......Au......", fontWeight = FontWeight.Bold, fontSize = 16.sp)
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
               /* val dataList = mutableListOf(30,60,90,50,70,10,100)
                val floatValue = mutableListOf<Float>()
                val dateList = mutableListOf(2,20,10,5,1,6,7)
                dateList.forEachIndexed{
                    index, i ->
                    floatValue.add(index = index, element = i.toFloat()/dateList.max().toFloat())
                }
                BarGraph(
                    graphBarData = floatValue,
                    xAxisScaleData = dateList,
                    barData_ =dataList ,
                    height = height/3,
                    roundType = BarType.TOP_CURVED,
                    barWidth = width/10+5.dp,
                    barColor = Purple500,
                    barArrangement =Arrangement.SpaceEvenly
                )*/
                MyCanva()
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
data class StackedData(
    val inputs: List<Float>,
    val colors: List<Color>,
    val names: List<String>
)

@Composable
@ReadOnlyComposable
internal fun stackedBarChartInputs() = (0..6).map {
    val inputs = listOf(20f,40f,60f,40f).toPercent()

    StackedData(
        inputs = inputs,
        colors = listOf(
            Color.Red,
            Color.Green,
            Color.Blue,
            Color.Red,
        ),
        names = listOf("ABK","TLK \n ADD \n ABK","ADD","AST")
    )
}

private fun List<Float>.toPercent(): List<Float> {
    return this.map { item ->
        item * 100 / this.sum()
    }
}
@Preview(showBackground = true)
@Composable
fun MyCanva(){
    val conf = LocalConfiguration.current;
    val width = conf.screenWidthDp.dp;
    val height = conf.screenHeightDp.dp;
    val borderColor = MaterialTheme.colorScheme.primary

    val density = LocalDensity.current

    val strokeWidth = with(density) { 2.dp.toPx() }

    Row(
        modifier = Modifier.then(
            Modifier
                .fillMaxWidth()
                .height(height / 2)
                .padding(15.dp)
                .drawBehind {

                    // draw X-Axis
                    drawLine(
                        color = borderColor,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = strokeWidth
                    )

                    // draw Y-Axis
                    drawLine(
                        color = borderColor,
                        start = Offset(0f, 0f),
                        end = Offset(0f, size.height),
                        strokeWidth = strokeWidth
                    )

                }
        ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
      stackedBarChartInputs().forEach{
          stackedData -> Column(modifier = Modifier.weight(2f)) {

          stackedData.inputs.forEachIndexed { index, input ->
              val itemHeight = remember(input) { input * height.value / 200 }

              Column(
                  modifier = Modifier
                      .padding(horizontal = 5.dp, vertical = 2.dp)
                      .height(itemHeight.dp)
                      .width(width / 10 + 100.dp)
                      .clip(RoundedCornerShape(10.dp))
                      .background(stackedData.colors[index])
              ,
                  verticalArrangement = Arrangement.Center,
                  horizontalAlignment = Alignment.CenterHorizontally
              ){
                    Text(text = stackedData.names[index],
                        color = if (stackedData.colors[index]== Color.Red) Color.White else (if(stackedData.colors[index]==Color.Blue) Color.Green else Color.Black),
                        style = TextStyle(
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold
                        )
                            )
              }
          }

      }
      }
    }

}
