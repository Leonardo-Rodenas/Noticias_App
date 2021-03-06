package com.example.noticias_app.Client

import com.example.noticias_app.Service.NoticiaService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NoticiaCliente {

    companion object {

        const val URL_BASE = "https://newsapi.org/v2/"

        fun obtenCliente(): NoticiaService {
            val retrofit = Retrofit.Builder().baseUrl(URL_BASE).addConverterFactory(
                GsonConverterFactory.create()
            ).build()
            return retrofit.create(NoticiaService::class.java)
        }

        //usar para pruebas, comentar obtencliente de arriba y descomentar el de abajo

        /*   fun obtenCliente(url:String): NoticiaService{
               val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(
                   GsonConverterFactory.create()).build()
               return retrofit.create(NoticiaService::class.java)
           }*/
    }

}