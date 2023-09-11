package com.ramseys.jobplan.Composables.Widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramseys.jobplan.Composables.DataClassRep.StackedData


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
                        color = if (stackedData.colors[index]== Color.Red) Color.White else (if(stackedData.colors[index]== Color.Blue) Color.Green else Color.Black),
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
