package com.example.noticias_app.Service


import com.example.noticias_app.model.Article
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface NoticiaService {
/*
    companion object{
        val APIKEY = BuildConfig.API_KEY
    }*/

    @GET("top-headlines")
    fun traerListadoDeNoticias(
        @Query("country", encoded = true) pais: String = "mx",
        @Query("apiKey", encoded = true) apiKey: String = "6d648fd5802448b09fe8a59863c5efc1"
    ): Call<List<Article>>

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
