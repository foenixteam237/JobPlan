package com.ramseys.jobplan.Composables.ui

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ramseys.jobplan.Composables.DataClassRep.DashItem
import com.ramseys.jobplan.Composables.Widget.gridView
import com.ramseys.jobplan.Data.Controlleur.ApiClient
import com.ramseys.jobplan.Data.Model.Unit
import com.ramseys.jobplan.Data.Request.UnitResponse
import com.ramseys.jobplan.R
import com.ramseys.jobplan.data.Model.UserItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ListTableau(user: UserItem) {
    val conf = LocalConfiguration.current
    val height = conf.screenHeightDp.dp
    val width = conf.screenWidthDp.dp
    val apiClient = ApiClient()
    val context = LocalContext.current

     var img by  remember {
         mutableIntStateOf(R.drawable.admin_amico)
     }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height / 2)
            ) {
                Image(
                    painter = painterResource(id = img),
                    contentDescription = "Time plan"
                )
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
                        Color.White,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )
            ) {
                var itemList: MutableList<DashItem> = remember {
                    mutableStateListOf<DashItem>()
                }

                //itemList = ArrayList<DashItem>()
                if (user.role.roleName == "Admin") {
                    apiClient.getApiService().getWorkplaceList(user.unit_id)
                        .enqueue(object : Callback<UnitResponse> {
                            override fun onResponse(
                                call: Call<UnitResponse>,
                                response: Response<UnitResponse>
                            ) {
                                val unit = response.body()

                                if (response.isSuccessful && unit != null) {
                                    unit.workplace.forEach { workplace ->
                                        itemList.add(
                                            DashItem(
                                                "Poste " + workplace.name,
                                                Color.Blue,
                                                R.drawable.unit2,
                                                workplace.name
                                            )
                                        )
                                    }
                                } else Toast.makeText(
                                    context,
                                    "Erreur de chargement" + response.body(),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                            override fun onFailure(
                                call: Call<UnitResponse>,
                                t: Throwable
                            ) {
                                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                            }

                        })
                } else {
                    img = R.drawable.commandant
                    val call: Call<ArrayList<Unit>> = apiClient.getApiService().getUnits()

                    call!!.enqueue(
                        object : Callback<ArrayList<Unit>?> {
                            override fun onResponse(
                                call: Call<ArrayList<Unit>?>,
                                response: Response<ArrayList<Unit>?>
                            ) {
                                if (response.isSuccessful) {

                                    var list: ArrayList<Unit> = ArrayList()

                                    list = response.body()!!

                                    for (i in 0 until list.size) {

                                        itemList.add(
                                            DashItem(
                                                "U"+list[i].unitCode,
                                                Color.Blue,
                                                getRessoucre(list[i].id),
                                                list[i].unitCode
                                            )
                                        )
                                    }
                                }
                            }

                            override fun onFailure(call: Call<ArrayList<Unit>?>, t: Throwable) {
                                Toast.makeText(context, "Erreur " + t.message, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    )

                    itemList.add(DashItem("Visualiser", Color.Blue, R.drawable.timeplan, "VT"))
                }

                gridView(context = context, itemList = itemList, user)
            }
        }
    }

}

fun getRessoucre(id:Int):Int{

    return when (id) {
        1 ->  R.drawable.unit1
        2 ->  R.drawable.unit2
        3 ->  R.drawable.unit3
        else -> {R.drawable.unit1}
    }
}