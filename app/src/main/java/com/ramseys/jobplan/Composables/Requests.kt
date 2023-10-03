package com.ramseys.jobplan.Composables

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.ramseys.jobplan.Data.Controlleur.ApiClient
import com.ramseys.jobplan.Data.Model.Unit
import com.ramseys.jobplan.Data.Controlleur.ApiConnexionInterface
import com.ramseys.jobplan.Data.Request.WorkPlacesRequest
import com.ramseys.jobplan.Data.RetrofitAPI
import com.ramseys.jobplan.data.Model.Nationality
import com.ramseys.jobplan.data.Model.Qualification
import com.ramseys.jobplan.data.Model.UserItem
import com.ramseys.jobplan.data.Model.Workplace
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getUnitData(unitList: MutableList<Unit>, ctx: Context) {

    val retro = ApiConnexionInterface().ApiConnexion()

    val retroApi = retro.create(RetrofitAPI::class.java)

    val call: Call<ArrayList<Unit>> = retroApi.getUnits()

    call.enqueue(
        object : Callback<ArrayList<Unit>?> {
            override fun onResponse(
                call: Call<ArrayList<Unit>?>,
                response: Response<ArrayList<Unit>?>
            ) {
                if (response.isSuccessful) {

                    var list: ArrayList<Unit> = ArrayList()

                    list = response.body()!!

                    for (i in 0 until list.size) {
                        unitList.add(list[i])
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Unit>?>, t: Throwable) {
                Toast.makeText(ctx, "Erreur de chargement " + t.message, Toast.LENGTH_SHORT).show()
            }
        }
    )
}

fun getQualificationData(qualificationList: MutableList<Qualification>, ctx: Context) {

    val retro = ApiConnexionInterface().ApiConnexion()

    val retroApi = retro.create(RetrofitAPI::class.java)

    val call: Call<ArrayList<Qualification>> = retroApi.getQualifications()

    call!!.enqueue(
        object : Callback<ArrayList<Qualification>?> {
            override fun onResponse(
                call: Call<ArrayList<Qualification>?>,
                response: Response<ArrayList<Qualification>?>
            ) {
                if (response.isSuccessful) {
                    var list: ArrayList<Qualification> = ArrayList()

                    list = response.body()!!

                    for (i in 0 until list.size) {
                        qualificationList.add(list[i])
                    }
                }
            }

            override fun onFailure(call: Call<ArrayList<Qualification>?>, t: Throwable) {
                Toast.makeText(ctx, "Erreur de chargement " + t.message, Toast.LENGTH_LONG).show()
            }
        }
    )
}

fun getJsonNationality(nationalitieList: MutableList<Nationality>, ctx: Context) {

    val retrofitAPI = ApiConnexionInterface().ApiConnexion().create(RetrofitAPI::class.java)

    val call: Call<ArrayList<Nationality>> = retrofitAPI.getNationality()

    call!!.enqueue(
        object : Callback<ArrayList<Nationality>?> {
            override fun onFailure(call: Call<ArrayList<Nationality>?>, t: Throwable) {
                Toast.makeText(ctx, "Erreur " + t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ArrayList<Nationality>?>,
                response: Response<ArrayList<Nationality>?>
            ) {
                if (response.isSuccessful) {
                    var list: ArrayList<Nationality> = ArrayList()

                    list = response.body()!!

                    for (i in 0 until list.size) {
                        nationalitieList.add(list[i])
                    }
                }
            }
        }
    )
}
@Composable
fun getWorkplaceUsers(context: Context, poste: String):MutableList<UserItem> {
    val apiClient = ApiClient()
    var userList: MutableList<UserItem> = remember {
        mutableListOf<UserItem>()
    }

    apiClient.getApiService().getUserList(WorkPlacesRequest(poste)).enqueue(
        object : Callback<Workplace> {
            override fun onResponse(call: Call<Workplace>, response: Response<Workplace>) {
                val workResponse = response.body()

                val usersList: List<UserItem>? = workResponse?.users
                if (response.isSuccessful && workResponse != null) {
                    if (usersList != null) {
                        userList.clear()
                        for (element in usersList) {
                            userList.add(element)
                            Toast.makeText(context, element.name, Toast.LENGTH_SHORT).show()
                        }
                    }
                } else Toast.makeText(
                    context,
                    response.errorBody().toString(),
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onFailure(call: Call<Workplace>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }

        }
    )
    return userList;
}