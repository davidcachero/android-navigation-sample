package es.utad.ejercicionavigation.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PeliculaDao {

    @Query("SELECT distinct(tipo) FROM pelicula")
    fun getTipos(): List<String>

    @Query("SELECT * FROM pelicula where tipo = :tipo")
    fun getPeliculasByTipo(tipo:String): List<Pelicula>

    @Query("SELECT * FROM pelicula where codigo = :id")
    fun getPeliculasById(id:Int): Pelicula

    @Insert
    fun insert(peiculas: List<Pelicula>)
}