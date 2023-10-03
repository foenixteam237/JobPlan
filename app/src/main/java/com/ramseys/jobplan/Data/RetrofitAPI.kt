package com.ramseys.jobplan.Data

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.ramseys.jobplan.Data.Model.AffectRequest
import com.ramseys.jobplan.Data.Model.AffectResponse
import com.ramseys.jobplan.Data.Model.Message
import com.ramseys.jobplan.Data.Model.Planning
import com.ramseys.jobplan.Data.Model.Program
import com.ramseys.jobplan.Data.Model.Unit
import com.ramseys.jobplan.Data.Request.LoginRequest
import com.ramseys.jobplan.Data.Request.LoginResponse
import com.ramseys.jobplan.Data.Request.MessageRequest
import com.ramseys.jobplan.Data.Request.PlanningResponse
import com.ramseys.jobplan.Data.Request.RegisterRequest
import com.ramseys.jobplan.Data.Request.RegisterResponse
import com.ramseys.jobplan.Data.Request.UnitResponse
import com.ramseys.jobplan.Data.Request.WorkPlacesRequest
import com.ramseys.jobplan.Data.Request.WorkplaceResponse
import com.ramseys.jobplan.Data.Request.ProgramResponse
import com.ramseys.jobplan.Data.Request.ProgramResquest
import com.ramseys.jobplan.Data.Request.statusResponse
import com.ramseys.jobplan.data.Model.Nationality
import com.ramseys.jobplan.data.Model.Qualification
import com.ramseys.jobplan.data.Model.UserItem
import com.ramseys.jobplan.data.Model.Workplace
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitAPI {

    @GET("users")
    fun
            getUsers(): Call<ArrayList<UserItem>>

    @GET(Constantes.UNITE)
    fun
            getUnits(): Call<ArrayList<Unit>>

    @GET(Constantes.QUALIFICATION)
    fun
            getQualifications(): Call<ArrayList<Qualification>>

    @GET(Constantes.NATIONALITY)
    fun
            getNationality(): Call<ArrayList<Nationality>>

    @GET(Constantes.WORKPLACELIST)
    fun
            getWorkplaceList(@Path("id") id: Int): Call<UnitResponse>

    @GET(Constantes.RECENTCHAT)
    fun
            getRecentChat(@Path("id") id: Int): Call<ArrayList<Message>>

    @GET(Constantes.CHAT)
    fun
            getConversation(
        @Path("idSender") idSender: Int,
        @Path("idReceiver") idReceiver: Int
    ): Call<ArrayList<Message>>

    @GET(Constantes.USERSLISTS)
    fun
            getUsersList(@Path("id") id: Int):Call<ArrayList<UserItem>>

    //Post request

    @POST(Constantes.LOGIN_URL)
    fun
            login(@Body request: LoginRequest): Call<LoginResponse>

    @POST(Constantes.USERS)
    fun
            register(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST(Constantes.WORKPLACE)
    fun
            getWorkplace(@Body request: WorkPlacesRequest): Call<ArrayList<WorkplaceResponse>>

    @POST(Constantes.USERLIST)
    fun
            getUserList(@Body request: WorkPlacesRequest): Call<Workplace>

    @POST(Constantes.PLANNING)
    fun
            setPlanning(@Body request: Planning): Call<PlanningResponse>

    @POST(Constantes.PROGRAM)
    fun
            setProgram(@Body request: Program): Call<ProgramResponse>

    @POST(Constantes.SEND)
    suspend fun sendMessage(
        @Body message: MessageRequest
    ): Response<SnapshotStateList<Message>>

    @POST(Constantes.STATUSCHANGE)
    fun
            statusChange(@Path("matricule") matricule : String): Call<statusResponse>
    @POST(Constantes.AFFECT)
    fun
            affectWorkplace(@Body affect: AffectRequest):Call<AffectResponse>

}