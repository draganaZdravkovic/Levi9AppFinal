package com.example.levi9appfinal.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.levi9appfinal.R
import com.example.levi9appfinal.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewLoginHere.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.buttonRegister.setOnClickListener {
            val firstName = binding.editTextFirstName.text.toString().trim()
            val lastName = binding.editTextLastName.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()
            val password = binding.editTextPassword.text.toString().trim()
            val repeatPassword = binding.editTextRepeatPassword.text.toString().trim()
            val typeOfRestaurant = binding.etTypeOfRestaurant.text.toString().trim()

            if(!passwordIsValid(password, repeatPassword)){
                return@setOnClickListener
            }

            if(!emailIsValid(email)){
                return@setOnClickListener
            }

            val sharedPreferences = context?.getSharedPreferences("Shared_pref",Context.MODE_PRIVATE)?: return@setOnClickListener
            with(sharedPreferences.edit()) {
                putString("FIRST_NAME", firstName)
                putString("LAST_NAME", lastName)
                putString("EMAIL", email)
                putString("PASSWORD", password)
                putString("REPEAT_PASSWORD", repeatPassword)
                putString("TYPE_OF_RESTAURANT", typeOfRestaurant)
                apply()
            }

           println(sharedPreferences.getString("FIRST_NAME", "nema nista"))
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun emailIsValid(email: String):Boolean {
        if(!email.endsWith("@gmail.com")) {
            Toast.makeText(context, "Email must end with @gmail.com!", Toast.LENGTH_LONG).show()
            return false
        } else if(email.isEmpty()){
            Toast.makeText(context, "Email is empty!", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun passwordIsValid(pass: String, passRepeat: String): Boolean {
        if((pass != passRepeat) ){
            Toast.makeText(context, "Passwords are not matching!", Toast.LENGTH_LONG).show()
            return false
        } else if(pass.isEmpty() || passRepeat.isEmpty()) {
            Toast.makeText(context, "Password is empty!", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}