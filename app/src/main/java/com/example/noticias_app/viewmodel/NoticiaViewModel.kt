package com.example.noticias_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noticias_app.model.Article
import com.example.noticias_app.repository.NoticiaRepositorio

class NoticiaViewModel : ViewModel() {

    private var repositorio = NoticiaRepositorio()

    fun traemeLaListaDeArticulosDelServer() {

        repositorio.traerListaDeArticulosEnRepo()

    }

    fun exponeNoticiasDeLaApi_EnVM(): MutableLiveData<List<Article>> {
        return repositorio.exponeNoticiasDeLaApi_EnRepo()
        Log.v("exponeNoticiasVM", "mensaje")
    }

    fun objetoNoticia(noticia: Article) : Article {
        return noticia
    }
}

// Intenté un ViewModel Factory, pero al darme cuenta que no tengo argumentos en el ViewModel original no lo necesito
// así que lo borre.