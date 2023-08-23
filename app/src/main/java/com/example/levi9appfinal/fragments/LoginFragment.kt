package com.example.levi9appfinal.fragments

import android.content.Context

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.levi9appfinal.R
import com.example.levi9appfinal.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

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

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.textViewSignUpHere.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
//
        binding.buttonLogin.setOnClickListener {
            val tvEmail = binding.editTextEmail.text.toString().trim()
            val tvPassword = binding.editTextPassword.text.toString().trim()


            val sharedPreferences = context?.getSharedPreferences("Shared_pref",Context.MODE_PRIVATE)?: return@setOnClickListener

            val email = sharedPreferences.getString("EMAIL", "")
            val password = sharedPreferences.getString("PASSWORD", "")

            if(tvEmail == "" || (tvEmail != email)) {
                Toast.makeText(context,"User doesn't exist!",Toast.LENGTH_LONG).show()
            } else if(tvPassword == "" || tvPassword != password) {
                Toast.makeText(context,"Wrong password!",Toast.LENGTH_LONG).show()
            } else {
                findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
                activity?.finish()
            }

            val name = sharedPreferences.getString("FIRST_NAME", "")

            Toast.makeText(context,"Welcome back $name",Toast.LENGTH_LONG).show()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}