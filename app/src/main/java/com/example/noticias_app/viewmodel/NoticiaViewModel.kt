package com.example.noticias_app.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noticias_app.model.Article
import com.example.noticias_app.repository.NoticiaRepositorio

class NoticiaViewModel : ViewModel() {

    private var repositorio = NoticiaRepositorio()

    fun traemeLaListaDeArticulosDelServer() {
        Log.v("expone traemeLaListaDeArticulosDelServer", repositorio.traerListaDeArticulosEnRepo().toString())
        repositorio.traerListaDeArticulosEnRepo()

    }

    fun exponeNoticiasDeLaApi_EnVM(): MutableLiveData<List<Article>> {

        Log.v("expone exponeNoticiasDeLaApi_EnVM", repositorio.exponeNoticiasDeLaApi_EnRepo().value.toString())
        return repositorio.exponeNoticiasDeLaApi_EnRepo()

    }

}

// Intenté un ViewModel Factory, pero al darme cuenta que no tengo argumentos en el ViewModel original no lo necesito
// así que lo borre.