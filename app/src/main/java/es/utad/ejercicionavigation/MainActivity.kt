package es.utad.ejercicionavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.utad.ejercicionavigation.database.DataRepository
import es.utad.ejercicionavigation.utils.JsonReader


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var dataRepository = DataRepository(this)
        var tipos = dataRepository.getTipos()

        if (tipos.isEmpty()){
            var jsonReader = JsonReader(resources)
            var listaPeliculas = jsonReader.readPeliculas()
            dataRepository.insert(listaPeliculas)
        }
    }
}