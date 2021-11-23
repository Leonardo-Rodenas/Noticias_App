package com.example.noticias_app.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.noticias_app.Client.NoticiaCliente
import com.example.noticias_app.model.Article
import com.example.noticias_app.model.NoticiaModel
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
                    listaDeArticulos.postValue(response.body()?.toString())
                }
            }

            override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                call.cancel()
            }
        })

    }

    fun exponeNoticiasDeLaApi_EnRepo(): MutableLiveData<List<Article>> {
        Log.v("exponeNoticiasDeLaApi_EnRepo", listaDeArticulos.toString())
        return listaDeArticulos
    }

    // BUSCAR

    fun buscarListaDeArticulosEnRepo(noticia: String, idioma: String, apikey: String) {

        val call = cliente.buscarListaDeNoticias(noticia, idioma, apikey)
        call.enqueue(object : Callback<List<Article>> {
            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                CoroutineScope(Dispatchers.IO).launch {
                    Log.v("buscarListaDeArticulosEnRepo", listaDeArticulos.value.toString())
                    listaDeArticulos.postValue(response.body()?.toString())
                }
            }

            override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                call.cancel()

            }
        })

    }

    fun exponeBusquedaDeNoticias_EnRepo(): MutableLiveData<List<Article>> {
        Log.v("exponeBusquedaNoticias_EnRepo", listaDeArticulos.toString())
        return listaDeArticulos
    }

}