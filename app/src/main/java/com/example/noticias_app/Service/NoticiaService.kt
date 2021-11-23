package com.example.noticias_app.Service


import com.example.noticias_app.model.Article
import com.example.noticias_app.model.NoticiaModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NoticiaService {
/*
    companion object{
        val APIKEY = BuildConfig.API_KEY
    }*/

    // https://newsapi.org/v2/top-headlines?country=mx&apiKey=6d648fd5802448b09fe8a59863c5efc1
    @GET("top-headlines")
    fun traerListadoDeNoticias(
        @Query("country", encoded = true) pais: String = "mx",
        @Query("apiKey", encoded = true) apiKey: String = "6d648fd5802448b09fe8a59863c5efc1"
    ): Call<List<Article>>

    // https://newsapi.org/v2/everything?q=bitcoin&apiKey=6d648fd5802448b09fe8a59863c5efc1

    // + language=es

    // https://newsapi.org/v2/everything?q=chile&language=es&apiKey=6d648fd5802448b09fe8a59863c5efc1
    @GET("everything")
    fun buscarListaDeNoticias(
        @Query(value = "q", encoded = true) loBuscado: String,
        @Query(value = "language", encoded = true) idioma: String,
        @Query("apiKey",encoded = true) apiKey: String = "6d648fd5802448b09fe8a59863c5efc1"
    ):Call<List<Article>>

}

/*
// Completa
// https://newsapi.org/v2/top-headlines?country=mx&apiKey=6d648fd5802448b09fe8a59863c5efc1

// Base
// https://newsapi.org/v2/

// Lo que cambia
// top-headlines

// La Query
// ?country=mx&apiKey=6d648fd5802448b09fe8a59863c5efc1
*/

//PARA HACER LA PARTE DE BUSCAR
// GET https://newsapi.org/v2/everything?q=bitcoin&apiKey=6d648fd5802448b09fe8a59863c5efc1