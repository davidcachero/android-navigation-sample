package es.utad.ejercicionavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import es.utad.ejercicionavigation.database.DataRepository


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    lateinit var editTextUsuario: EditText
    lateinit var editTextPassword: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var v = inflater.inflate(R.layout.fragment_login, container, false)
        editTextUsuario = v.findViewById<EditText>(R.id.editTextUsuario)
        editTextPassword = v.findViewById<EditText>(R.id.editTextPassword)

        val buttonLogin = v.findViewById<Button>(R.id.buttonLoginOk)

        buttonLogin.setOnClickListener{
            procesarLogin()
        }

        return v
    }

    private fun procesarLogin(){
        val dataRepository = DataRepository(requireContext())

        if (dataRepository.countUsuario() == 0){
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        else{
            if (dataRepository.existeUsuario(editTextUsuario.text.toString(), editTextPassword.text.toString())){
                findNavController().navigate(R.id.action_loginFragment_to_listFragment)
            }
            else{
                Toast.makeText(requireContext(), "Datos incorrectos", Toast.LENGTH_LONG)
            }
        }

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply { }
    }
}