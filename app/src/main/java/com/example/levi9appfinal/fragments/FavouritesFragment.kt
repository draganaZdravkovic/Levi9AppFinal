package com.example.levi9appfinal.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.levi9appfinal.R
import com.example.levi9appfinal.adapters.RestaurantAdapter
import com.example.levi9appfinal.api.ApiInterface
import com.example.levi9appfinal.api.ApiUtilities
import com.example.levi9appfinal.databinding.FragmentFavouritesBinding
import com.example.levi9appfinal.repository.ResultRepository
import com.example.levi9appfinal.viewmodels.ResultCousineViewModel
import com.example.levi9appfinal.viewmodels.ResultCousineViewModelFactory
import com.example.levi9appfinal.viewmodels.ResultViewModel
import com.example.levi9appfinal.viewmodels.ResultViewModelFactory


class FavouritesFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var resultViewModel: ResultCousineViewModel
    val data = ArrayList<com.example.levi9appfinal.models.Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = context?.getSharedPreferences("Shared_pref", Context.MODE_PRIVATE)?: return
        val type = sharedPreferences.getString("TYPE_OF_RESTAURANT", "Pizza").toString()

        val apiInference = ApiUtilities.getInstance().create(ApiInterface::class.java)

        val resultRepository = ResultRepository(apiInference)

        resultViewModel = ViewModelProvider(this, ResultCousineViewModelFactory(resultRepository, type))
            .get(ResultCousineViewModel::class.java)

        resultViewModel.result.observe(this) { it ->
            println("onCreate: ${it.toString()}")
            it.results.iterator().forEach {
                data.add(it)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvRestaurantsFavourites)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        val adapter = RestaurantAdapter(data)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : RestaurantAdapter.onItemClickListener {
            override fun onItemClick(restaurantDetails: com.example.levi9appfinal.models.Result) {
                println("res: ${restaurantDetails.name}")
                findNavController().navigate(R.id.action_favouritesFragment_to_restaurantDetailFragment
                    ,Bundle().apply {
                        putString("restaurantName", restaurantDetails.name)
                        putString("restaurantAddress", restaurantDetails.attribution[0].url)
                        putString("restaurantDesc", restaurantDetails.snippet)
                        putString("restaurantImage", if (restaurantDetails.images.isNotEmpty()) restaurantDetails.images[0].source_url else "")
                    }
                )
            }
        })
    }
}