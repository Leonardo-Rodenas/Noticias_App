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

    // Para pruebas unitarias --> Descomentar var cliente_pruebas y comentar var cliente

    //private var cliente_pruebas = NoticiaCliente.obtenCliente(NoticiaCliente.URL_BASE)

    var listaDeArticulos = MutableLiveData<List<Article>>()

    fun traerListaDeArticulosEnRepo() {

        val call = cliente.traerListadoDeNoticias()
        call.enqueue(object : Callback<NoticiaModel> {
            override fun onResponse(call: Call<NoticiaModel>, response: Response<NoticiaModel>) {
                CoroutineScope(Dispatchers.IO).launch {
                    listaDeArticulos.postValue(response.body()?.articles)
                }
            }

            override fun onFailure(call: Call<NoticiaModel>, t: Throwable) {
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
        call.enqueue(object : Callback<NoticiaModel> {
            override fun onResponse(call: Call<NoticiaModel>, response: Response<NoticiaModel>) {
                CoroutineScope(Dispatchers.IO).launch {
                    Log.v("buscarListaDeArticulosEnRepo", listaDeArticulos.value.toString())
                    listaDeArticulos.postValue(response.body()?.articles)
                }
            }

            override fun onFailure(call: Call<NoticiaModel>, t: Throwable) {
                call.cancel()

            }
        })

    }

    fun exponeBusquedaDeNoticias_EnRepo(): MutableLiveData<List<Article>> {
        Log.v("exponeBusquedaNoticias_EnRepo", listaDeArticulos.toString())
        return listaDeArticulos
    }

}