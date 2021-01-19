package es.utad.ejercicionavigation.utils

import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.utad.ejercicionavigation.R
import es.utad.ejercicionavigation.database.Pelicula
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class JsonReader(val resources: Resources) {
    fun readPeliculas():List<Pelicula>{
        val gson = Gson()
        var bufferedReader= BufferedReader(InputStreamReader(resources.openRawResource(R.raw.catalogo)))

        var json = bufferedReader.readLine()

        val arrayPeliculaType = object : TypeToken<Array<Pelicula>>() {}.type

        var peliculas: Array<Pelicula> = gson.fromJson(json, arrayPeliculaType)
        return  Arrays.asList(*peliculas)
    }
}