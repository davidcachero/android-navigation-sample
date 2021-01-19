package es.utad.ejercicionavigation.database

import android.content.Context
import android.os.AsyncTask

class DataRepository(context: Context) {
    private val peliculaDao: PeliculaDao? = AppDatabase.getInstance(context)?.peliculaDao()
    private val usuarioDao: UsuarioDao? = AppDatabase.getInstance(context)?.usuarioDao()

    fun existeUsuario(usuario:String, password:String): Boolean{
        if (usuarioDao!=null){
            var datos = ArrayList<String>()
            datos.add(usuario)
            datos.add(password)
            return (ExisteUsuarioAsyncTask(usuarioDao).execute(datos).get()==1)
        }
        return false
    }

    fun countUsuario(): Int{
        if (usuarioDao != null){
            return CountUsuarioAsyncTask(usuarioDao).execute().get()
        }
        return 0
    }

    fun insert(usuario: Usuario):Int {
        if (usuarioDao != null){
            return InsertUsuarioAsyncTask(usuarioDao).execute(usuario).get()
        }
        return -1
    }

    private class ExisteUsuarioAsyncTask(private val usuarioDao: UsuarioDao) : AsyncTask<List<String>, Void, Int>() {
        override fun doInBackground(vararg datos: List<String>): Int? {
            try {
                return usuarioDao.countUsuarioByUsuarioPassword(datos[0][0], datos[0][1])
            }
            catch (exception: Exception){
                return 0
            }
        }
    }

    private class CountUsuarioAsyncTask(private val usuarioDao: UsuarioDao) : AsyncTask<Void, Void, Int>() {

        override fun doInBackground(vararg p0: Void?): Int? {
            try {
                return usuarioDao.countUsuario()
            }
            catch (exception: Exception){
                return 0
            }
        }
    }

    private class InsertUsuarioAsyncTask(private val usuarioDao: UsuarioDao) : AsyncTask<Usuario, Void, Int>() {
        override fun doInBackground(vararg usuario: Usuario): Int? {
            try {
                usuarioDao.insert(usuario[0])
                return 0
            }
            catch (exception: Exception){
                return -1
            }
        }
    }

    /******************************PELICULAS **********************************/
    fun insert(peliculas: List<Pelicula>):Int {
        if (peliculaDao != null){
            return InsertPeliculasAsyncTask(peliculaDao).execute(peliculas).get()
        }
        return -1
    }

    fun getTipos():List<String> {
        if (peliculaDao != null){
            return getTiposAsyncTask(peliculaDao).execute().get()
        }
        return ArrayList<String>()
    }

    fun getPeliculas(tipo: String):List<Pelicula> {
        if (peliculaDao != null){
            return getPeliculasAsyncTask(peliculaDao).execute(tipo).get()
        }
        return ArrayList<Pelicula>()
    }

    fun getPelicula(id: Int):Pelicula? {
        if (peliculaDao != null){
            return getPeliculaByIdAsyncTask(peliculaDao).execute(id).get()
        }
        return null
    }

    private class InsertPeliculasAsyncTask(private val peliculaDao: PeliculaDao) : AsyncTask<List<Pelicula>, Void, Int>() {
        override fun doInBackground(vararg peliculas: List<Pelicula>): Int? {
            try {
                peliculaDao.insert(peliculas[0])
                return 0
            }
            catch (exception: Exception){
                return -1
            }
        }
    }

    private class getPeliculaByIdAsyncTask(private val peliculaDao: PeliculaDao) : AsyncTask<Int, Void, Pelicula?>() {
        override fun doInBackground(vararg id: Int?): Pelicula? {
            try {
                var pelicula = id[0]?.let { peliculaDao.getPeliculasById(it) }
                return pelicula
            }
            catch (exception: Exception){
                return null
            }
        }
    }

    private class getTiposAsyncTask(private val peliculaDao: PeliculaDao) : AsyncTask<Void, Void, List<String>>() {
        override fun doInBackground(vararg p0: Void?): List<String> {
            try {
                var tipos = peliculaDao.getTipos()
                return tipos
            }
            catch (exception: Exception){
                return ArrayList<String>()
            }
        }
    }

    private class getPeliculasAsyncTask(private val peliculaDao: PeliculaDao) : AsyncTask<String, Void, List<Pelicula>>() {
        override fun doInBackground(vararg tipo: String): List<Pelicula> {
            try {
                var peliculas = peliculaDao.getPeliculasByTipo(tipo[0])
                return peliculas
            }
            catch (exception: Exception){
                return ArrayList<Pelicula>()
            }
        }
    }

}