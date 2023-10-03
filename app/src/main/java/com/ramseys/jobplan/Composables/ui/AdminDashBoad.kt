package com.ramseys.jobplan.Composables.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.ramseys.jobplan.Composables.Widget.gridView
import com.ramseys.jobplan.R
import com.ramseys.jobplan.data.Model.Nationality
import com.ramseys.jobplan.data.Model.Qualification
import com.ramseys.jobplan.data.Model.Role
import com.ramseys.jobplan.data.Model.UserItem
import com.ramseys.jobplan.data.Model.Workplace
import com.ramseys.jobplan.ui.theme.JOBPLANTheme

class AdminDashBoad : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JOBPLANTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val user = intent.getSerializableExtra("user") as? UserItem

                    Scaffold() {
                        if (user != null) {
                            AdminScreen(user)
                        } else Toast.makeText(
                            LocalContext.current,
                            "Erreur de chargement de l'utilisateur",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun AdminScreen(user: UserItem, modifier: Modifier = Modifier) {
    val context = LocalContext.current;
    val height = LocalConfiguration.current.screenHeightDp.dp
    val width = LocalConfiguration.current.screenWidthDp

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
                    .height(height / 2),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.admin_amico),
                    contentDescription = "Admin",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height / 3 + 30.dp)
                )
                Row {
                    Text(
                        text = "M/Mme ${user.name}",
                        style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    )
                    Spacer(Modifier.width(20.dp))
                    Text(text = "Qualification:${user.qualification.name}", style = TextStyle(
                        fontSize = 10.sp
                    ))
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Rôle: ${user.role.roleName}")
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height / 2)
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
            ) {
                lateinit var itemList: List<DashItem>
                itemList = ArrayList<DashItem>()

                if (user.role.roleName == "Admin"){
                    itemList = itemList + DashItem(
                        "Tableaux de Services", Color.Blue,
                        R.drawable.timeplan, "TS"
                    )
                    itemList = itemList + DashItem(
                        "Gérer utilisateur", Color.Blue,
                        R.drawable.user_flow, "GU"
                    )
                    itemList =
                        itemList + DashItem("Messagerie", Color.Blue, R.drawable.messagerie, "SMS")

                }else{
                    itemList = itemList + DashItem(
                        "Tableaux de Services", Color.Blue,
                        R.drawable.timeplan, "TS"
                    )
                    itemList = itemList + DashItem(
                        "Droit d'accès", Color.Blue,
                        R.drawable.access_right, "DA"
                    )
                    itemList = itemList + DashItem(
                        "Gérer utilisateur", Color.Blue,
                        R.drawable.user_flow, "GU"
                    )
                    itemList =
                        itemList + DashItem("Messagerie", Color.Blue, R.drawable.messagerie, "SMS")
                }

                gridView(context = context, itemList, user)
            }
        }
    }
}

