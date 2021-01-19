package es.utad.ejercicionavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import es.utad.ejercicionavigation.database.DataRepository
import es.utad.ejercicionavigation.database.Pelicula


/**
 * A simple [Fragment] subclass.
 * Use the [FichaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FichaFragment : Fragment() {

    lateinit var textViewTitulo: TextView
    lateinit var textViewTipo: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_ficha, container, false)
        textViewTitulo = v.findViewById(R.id.textViewFichaTitulo)
        textViewTipo = v.findViewById(R.id.textViewFichaTipo)

        val args: FichaFragmentArgs by navArgs()
        //val dataRepository = DataRepository(requireContext())
        //val pelicula = dataRepository.getPelicula(args.idPelicula)

        val pelicula = args.idPelicula["pelicula"] as Pelicula

        if (pelicula != null) {
            textViewTipo.text = pelicula.tipo
            textViewTitulo.text = pelicula.titulo
        }

        return v
    }

    companion object {

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            FichaFragment().apply { }
    }
}