package com.example.noticias_app

import androidx.test.platform.app.InstrumentationRegistry
import java.io.InputStreamReader

object FileReader {

    object FileReader {

        fun lectorJson(archivo:String):String
        {
            val input = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext.assets.open(archivo)
            val builder = StringBuilder()
            val lector = InputStreamReader(input,"UTF-8")
            lector.readLines().forEach {
                builder.append(it)
            }
            return builder.toString()
        }
    }

}