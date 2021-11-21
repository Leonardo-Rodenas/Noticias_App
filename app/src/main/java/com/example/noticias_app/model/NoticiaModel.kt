package com.example.noticias_app.model

data class NoticiaModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)