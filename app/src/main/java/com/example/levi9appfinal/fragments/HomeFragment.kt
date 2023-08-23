package com.example.levi9appfinal.fragments

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
import com.example.levi9appfinal.models.Restaurants
import com.example.levi9appfinal.repository.ResultRepository
import com.example.levi9appfinal.viewmodels.ResultViewModel
import com.example.levi9appfinal.viewmodels.ResultViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class HomeFragment : Fragment() {

    private lateinit var resultViewModel: ResultViewModel
    var data = ArrayList<com.example.levi9appfinal.models.Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val apiInference = ApiUtilities.getInstance().create(ApiInterface::class.java)

        val resultRepository = ResultRepository(apiInference)

        resultViewModel = ViewModelProvider(this, ResultViewModelFactory(resultRepository))
            .get(ResultViewModel::class.java)

        resultViewModel.result.observe(this) { it ->
//            println("onCreate: ${it.toString()}")
            it.results.iterator().forEach {
                data.add(it)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvRestaurants)
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        val adapter = RestaurantAdapter(data)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : RestaurantAdapter.onItemClickListener {
            override fun onItemClick(restaurantDetails: com.example.levi9appfinal.models.Result) {
                findNavController().navigate(R.id.action_homeFragment_to_restaurantDetailFragment
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