package com.example.noticias_app.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.noticias_app.Client.NoticiaCliente
import com.example.noticias_app.model.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NoticiaRepositorio {

    private var cliente = NoticiaCliente.obtenCliente()

    //private var service = NoticiaService.traerListadoDeNoticias()
    var listaDeArticulos = MutableLiveData<List<Article>>()

    fun traerListaDeArticulosEnRepo() {

        val call = cliente.traerListadoDeNoticias()
        call.enqueue(object : Callback<List<Article>> {
            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {

                CoroutineScope(Dispatchers.IO).launch {
                    response.body().let {
                        Log.v("log_en_repo_traerlistaserver", response.body().toString())
                        listaDeArticulos.postValue(it)
                    }
                }

            }

            override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                call.cancel()
            }

        })

    }

    fun exponeNoticiasDeLaApi_EnRepo(): MutableLiveData<List<Article>> {
        return listaDeArticulos
    }



}