package com.ramseys.jobplan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramseys.jobplan.Composables.Widget.BarGraph
import com.ramseys.jobplan.Composables.Widget.BarType
import com.ramseys.jobplan.ui.theme.JOBPLANTheme
import com.ramseys.jobplan.ui.theme.Purple500
import com.ramseys.jobplan.ui.theme.primaryColor
import kotlin.math.round

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
                       Icon(painter = painterResource(id = R.drawable.baseline_more_vert_24), contentDescription = null, modifier = Modifier.height(30.dp), tint = Color.Black)

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
    val colors: List<Color>
)

@Composable
@ReadOnlyComposable
internal fun stackedBarChartInputs() = (0..6).map {
    val inputs = listOf(20f,20f,60f,70f).toPercent()


    StackedData(
        inputs = inputs,
        colors = listOf(
            Color.Green,
            Color.Blue,
            Color.Red,
            Color.DarkGray,
        )
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
    val textPaint = remember {
        Paint().apply {
            color = Color.Black.hashCode()
            textAlign = Paint.Align.CENTER
            textSize = density.run { 12.sp.toPx() }
        }
    }
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

              val itemHeight = remember(input) { input * height.value / 100 }

              Spacer(
                  modifier = Modifier
                      .padding(horizontal = 5.dp, vertical = 2.dp)
                      .height(itemHeight.dp)
                      .width(width / 10 + 100.dp)
                      .clip(RoundedCornerShape(10.dp))
                      .background(stackedData.colors[index])
              )
          }

      }
      }
    }

}

@Composable fun <T> Table(
    columnCount: Int,
    cellWidth: (index: Int) -> Dp,
    data: List<T>,
    modifier: Modifier = Modifier,
    headerCellContent: @Composable (index:Int)-> Unit,
    cellContent:@Composable (index: Int, item: T)->Unit
    ){
    val conf = LocalConfiguration.current;
    val width = conf.screenWidthDp.dp;
    val height = conf.screenHeightDp.dp;
    Surface(
        modifier = modifier
            .width(width)
            .height(height / 2)
            .padding(10.dp)
    ) {
        LazyRow(modifier = Modifier
            .padding(16.dp)
            .width(width)){
            items((0 until columnCount).toList()){
                columnIndex->
                Column {
                    (0..data.size).forEach{
                        index->
                        Surface(
                            border = BorderStroke(1.dp, Color.LightGray),
                            contentColor = Color.Black,
                            modifier = Modifier.width(cellWidth(columnIndex))
                        ) {
                            if (index == 0){
                                headerCellContent(columnIndex)
                            }else{
                                cellContent(columnIndex, data[index-1])
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Table(){
    class Person {
        var name: String
        var age: Int
        var status: Boolean
        var email: String
        constructor(name: String, age: Int, status: Boolean, email: String){
            this.name = name
            this.age = age
            this.email = email
            this.status = status
        }
    }
    val people = listOf(
        Person("Alex",21,false,"alex@yahoo.fr"),
        Person("Toukam", 20, true, "toukamngamini@gmail.com"),
        Person("ATOH BARGA",19,false, "atoh@gmail.com")
    )
    val cellWidth:(Int)->Dp={
        index->when(index){
            2->50.dp
            3->50.dp
            else -> 50.dp
        }
    }
    val headerCellTitle: @Composable (Int) -> Unit = { index ->
        val value = when (index) {
            0 -> "Name"
            1 -> "Age"
            2 -> "Has driving license"
            3 -> "Email"
            else -> ""
        }

        Text(
            text = value,
            fontSize = 15.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Black
        )
    }
    val cellText: @Composable (Int, Person) -> Unit = { index, item ->
        val value = when (index) {
            0 -> item.name
            1 -> item.age.toString()
            2 -> if (item.status) "YES" else "NO"
            3 -> item.email
            else -> ""
        }

        Text(
            text = value,
            fontSize = 10.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }

    Table(
        columnCount = 7,
        cellWidth = cellWidth,
        data = people,
        modifier = Modifier.verticalScroll(rememberScrollState()),
        headerCellContent = headerCellTitle,
        cellContent = cellText
    )
}
