package com.example.levi9appfinal.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.navigation.fragment.findNavController
import com.example.levi9appfinal.R
import com.example.levi9appfinal.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btLogout.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_loginActivity)
        }

        val sharedPreferences = context?.getSharedPreferences("Shared_pref", Context.MODE_PRIVATE)?: return

        var firstName = sharedPreferences.getString("FIRST_NAME", "First name").toString()
        var lastName = sharedPreferences.getString("LAST_NAME", "Last name").toString()
        var email = sharedPreferences.getString("EMAIL", "Email").toString()
        var typeOfRestaurant = sharedPreferences.getString("TYPE_OF_RESTAURANT", "Type of restaurant").toString()

        binding.editTextFirstName.setText(firstName)
        binding.editTextLastName.setText(lastName)
        binding.editTextEmail.setText(email)
        binding.etTypeOfRestaurant.setText(typeOfRestaurant)

        binding.tbEditFirstName.setOnClickListener{
            if(binding.tbEditFirstName.isChecked) {
                binding.editTextFirstName.requestFocus()
            }
            else {
                binding.editTextFirstName.clearFocus()
                with(sharedPreferences.edit()) {
                    putString("FIRST_NAME", binding.editTextFirstName.text.toString().trim())
                    apply()
                }
                it.hideKeyboard()
            }
        }

        binding.tbEditLastName.setOnClickListener{
            if(binding.tbEditLastName.isChecked) {
                binding.editTextLastName.requestFocus()
            }
            else {
                binding.editTextLastName.clearFocus()
                with(sharedPreferences.edit()) {
                    putString("LAST_NAME", binding.editTextLastName.text.toString().trim())
                    apply()
                }
                it.hideKeyboard()
            }
        }

        binding.tbEditTypeOfRestaurant.setOnClickListener{
            if(binding.tbEditTypeOfRestaurant.isChecked) {
                binding.etTypeOfRestaurant.requestFocus()
            }
            else {
                binding.etTypeOfRestaurant.clearFocus()
                with(sharedPreferences.edit()) {
                    putString("TYPE_OF_RESTAURANT", binding.etTypeOfRestaurant.text.toString().trim())
                    apply()
                }
                it.hideKeyboard()
            }
        }
    }

    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}