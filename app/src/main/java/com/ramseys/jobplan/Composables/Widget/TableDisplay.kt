package com.ramseys.jobplan.Composables.Widget

import android.widget.Toast
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramseys.jobplan.Composables.DataClassRep.StackedData
import com.ramseys.jobplan.Data.Model.Planning


@Composable
@ReadOnlyComposable
internal fun stackedBarChartInputs() = (0..6).map {
    val inputs = listOf(20f, 40f, 50f, 40f).toPercent()

    StackedData(
        inputs = inputs,
        colors = listOf(
            Color.Red,
            Color.Green,
            Color.Blue,
            Color.Red,
        ),
        names = listOf(listOf("ABK", "ADD"), listOf("LK", "CY"), listOf("PR"), listOf("ADD"))
    )
}

private fun List<Float>.toPercent(): List<Float> {
    return this.map { item ->
        item * 100 / this.sum()
    }
}

@Composable
fun MyCanva(planning: Planning) {
    val conf = LocalConfiguration.current;
    val width = conf.screenWidthDp.dp;
    val height = conf.screenHeightDp.dp;
    val borderColor = MaterialTheme.colorScheme.primary
    val context = LocalContext.current
    var hb  = mutableListOf<String>()
    var qm  = mutableListOf<String>()
    var qs  = mutableListOf<String>()
    var compteur by remember {
        mutableStateOf(0)
    }
    var name: MutableList<List<String>> = listOf(listOf(""), listOf(""), listOf(""), listOf("")).toMutableList()

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

        stackedBarChartInputs().forEachIndexed { index, stackedData ->
            qm.clear()
            qs.clear()
            hb.clear()
            planning.programs?.forEach { program ->
                run {
                    name.clear()
                    if (program.idDay == index+1) {

                        when (program.idHour) {
                            1 -> {
                                qm.add(program.user.name)
                            }

                            2 -> {
                                qs.add(program.user.name)
                            }

                            3 -> {
                                hb.add(program.user.name)
                            }
                        }

                        //Toast.makeText(context, name.toString(), Toast.LENGTH_SHORT).show()
                    }else name = listOf(listOf("NP"), listOf("NP"), listOf("NP"), listOf("NP")).toMutableList()
                    name = listOf(qm.toList(), qs.toList(), hb.toList(), listOf("ADD")).toMutableList()
                    //Toast.makeText(context, name.toString(), Toast.LENGTH_LONG).show()
                }

            }


            Column(modifier = Modifier.weight(2f)) {
                stackedData.inputs.forEachIndexed { it, input ->
                    val itemHeight = remember(input) { input * height.value / 200 }

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 2.dp)
                            .height(itemHeight.dp)
                            .width(width / 10 + 100.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(stackedData.colors[it]),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = if(name[it].isEmpty()) "NP" else name[it].toString(),
                            color = if (stackedData.colors[it] == Color.Red) Color.White else (if (stackedData.colors[it] == Color.Blue) Color.Green else Color.Black),
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

