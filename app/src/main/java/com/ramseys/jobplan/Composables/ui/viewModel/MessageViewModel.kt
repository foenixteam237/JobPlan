package com.ramseys.jobplan.Composables.ui.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramseys.jobplan.Data.Controlleur.ApiClient
import com.ramseys.jobplan.Data.Model.Message
import com.ramseys.jobplan.Data.Request.MessageRequest
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MessageViewModel : ViewModel() {

    val apiClient = ApiClient()
    private val message_list: MutableList<Message> = mutableStateListOf()


    val message: MutableList<Message> = message_list

    fun refresh(idSender:Int, idReceiver:Int, context: Context){

        apiClient.getApiService().getConversation(idSender, idReceiver).enqueue(
            object : retrofit2.Callback<ArrayList<Message>>{
                override fun onResponse(
                    call: Call<ArrayList<Message>>,
                    response: Response<ArrayList<Message>>
                ) {
                    if (response.isSuccessful){
                        response.body()!!.forEach {
                            message -> message_list.add(message)
                        }
                    }
                }

                override fun onFailure(call: Call<ArrayList<Message>>, t: Throwable) {
                    Toast.makeText(context, "Erreur"+ t.message, Toast.LENGTH_LONG).show()
                }

            }
        )
    }
    fun addMessage(message: Message) {

        message_list.add(message)
    }

    fun sendMessage(message: Message, context: Context) {
        addMessage(message)
        viewModelScope.launch {
            val response = apiClient.getApiService().sendMessage(MessageRequest(message.idReceiver!!, idSender = message.idSender!!,message.content!!))
            if (response.isSuccessful) {
                response.body()!!.forEach {
                    addMessage(it)
                }
            }else{
                Toast.makeText(context, "Erreur d'envoie du message\n"+response.body() +" \n"+response.message()+ "\n "+response.raw(), Toast.LENGTH_LONG).show()
            }
        }
    }
}