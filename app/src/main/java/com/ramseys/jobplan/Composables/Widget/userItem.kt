package com.ramseys.jobplan.Composables.Widget

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.ramseys.jobplan.Composables.ui.UserProfile
import com.ramseys.jobplan.Composables.ui.screens.Profile
import com.ramseys.jobplan.data.Model.UserItem


@Composable
fun userItem(user: UserItem) {

    val contxt = LocalContext.current
    Row(
        Modifier.padding(10.dp).clickable {
             val intent = Intent(contxt, UserProfile::class.java)
            intent.putExtra("user",user)
            startActivity(contxt, intent, null)
        },
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Column(
            Modifier
                .width(50.dp)
                .height(50.dp)
                .background(
                    Color.Transparent,
                    shape = RoundedCornerShape(50.dp)
                )
                .border(2.dp, Color.Cyan, shape = RoundedCornerShape(50.dp)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = diminutif(user.name + if(user.fisrtName == null) "" else " " +user.fisrtName), style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        }
        Row(
            Modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Row {
                    Text(
                        text = user.name + if(user.fisrtName == null)" " else " ${user.fisrtName}",
                        style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    )
                }
                Row {
                    Text(
                        text = "Qualication: ${user.qualification.name}",
                        style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Thin)
                    )
                }

            }
            Column(
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.End,
               modifier =  Modifier.fillMaxSize()
            ) {
                Row {
                    Text(
                        text = "Status: ${if (user.status == 1) "Validé" else "Non Validé"}",
                        style = TextStyle(fontSize = 10.sp, fontWeight = FontWeight.Bold, color = if (user.status == 1) Color.Cyan else Color.Red)
                    )
                }
            }
        }

    }
}

fun diminutif(nom: String): String {
    val mots = nom.split(" ") // Sépare le nom en mots individuels
    val diminutifs = mots.map { it.take(1) }
    return diminutifs.joinToString("") // Combine les diminutifs en un seul mot
}
