package es.utad.ejercicionavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.utad.ejercicionavigation.adapters.PeliculasAdapter
import es.utad.ejercicionavigation.database.DataRepository


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {
    lateinit var recyclerViewLista: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  inflater.inflate(R.layout.fragment_list, container, false)
        recyclerViewLista = v.findViewById<RecyclerView>(R.id.recyclerviewlista)
        var spinnerTipo = v.findViewById<Spinner>(R.id.spinnerTipo)
        var dataRepository = DataRepository(requireContext())
        var tipos = dataRepository.getTipos()
        var tipoAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, tipos)
        spinnerTipo.adapter = tipoAdapter

        spinnerTipo.onItemSelectedListener= object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, position: Int, id: Long) {
                rellenarListaPeliculas(tipos[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // write code to perform some action
            }
        }
        return v
    }

    fun rellenarListaPeliculas(tipo:String) {
        var dataRepository = DataRepository(requireContext())
        var peliculas = dataRepository.getPeliculas(tipo)
        val adapter = PeliculasAdapter(peliculas) /*{pelicula ->
                val action = ListFragmentDirections.actionListFragmentToFichaFragment(pelicula.codigo)
                findNavController().navigate(action)
        }*/
        recyclerViewLista.setAdapter(adapter)
        recyclerViewLista.setLayoutManager(LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false))
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ListFragment().apply { }
    }
}