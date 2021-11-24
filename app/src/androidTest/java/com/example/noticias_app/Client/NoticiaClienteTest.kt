package com.example.noticias_app.Client

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.noticias_app.FileReader
import com.google.common.truth.Truth
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoticiaClienteTest {

    /* Para ejecutar estas pruebas, realizar los cambios descritos en NoticiaCliente y NoticiaRepositorio
       y descomentar los métodos test_apiExitosa y test_apiError en esta misma clase (leer el encabezado de ambos)
    */

    private var server = MockWebServer()
    private val body = FileReader.FileReader.lectorJson("Noticia.json")

    @Before
    fun setUp() {
        server.start(8080)
        server.enqueue(MockResponse().setResponseCode(200).setBody(body))
        server.url("test/top-headlines?country=mx&apiKey=6d648fd5802448b09fe8a59863c5efc1")
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

//    Si el resultado de esta prueba da los tickets verdes, la prueba se considera exitosa pues
//    lo que esperamos obtener y lo que obtenemos (en este caso la descripción "Este
//    domingo se dio a conocer que el presidente López Obrador.... " SON IGUALES

/*    @Test
    fun test_apiExitosa() {
        val call =
            NoticiaCliente.obtenCliente("http://localhost:8080/test/").traerListadoDeNoticias()
        var noticia = call.execute().body()
        Truth.assertThat(noticia!!.articles[0].description.toString())
            .isEqualTo("Este domingo se dio a conocer que el presidente López Obrador (AMLO) acudirá el miércoles 24 y el jueves 25 a Zacatecas para presentar su “Plan de Apoyo”")
    }*/

//    Si el resultado de esta prueba da la cruz naranja (marcada como error), la pruebas se
//    considera exitosa pues lo que esperamos obtener y lo que obtenemos (en este caso la descripción "Este
//    domingo se dio a conocer que el presidente López Obrador.... " SON DIFERENTES

/*    @Test
    fun test_apiError() {
        val call =
            NoticiaCliente.obtenCliente("http://localhost:8080/test/").traerListadoDeNoticias()
        var noticia = call.execute().body()
        Truth.assertThat(noticia!!.articles[5].description.toString())
            .isEqualTo("Este domingo se dio a conocer que el presidente López Obrador (AMLO) acudirá el miércoles 24 y el jueves 25 a Zacatecas para presentar su “Plan de Apoyo”")
    }*/

}