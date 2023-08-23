package com.example.levi9appfinal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.levi9appfinal.R
import com.example.levi9appfinal.databinding.FragmentRestaurantDetailBinding

class RestaurantDetailFragment : Fragment() {

    private var _binding: FragmentRestaurantDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRestaurantDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val restName = requireArguments().getString("restaurantName").toString()

        var btBack = view.findViewById<Button>(R.id.btBack)
        btBack.text = restName

        var tvRestaurantName = view.findViewById<TextView>(R.id.tvRestaurantName)
        tvRestaurantName.text = restName

        var tvRestaurantAdress = view.findViewById<TextView>(R.id.tvRestaurantAddress)
        tvRestaurantAdress.text = requireArguments().getString("restaurantAddress").toString()

        var tvRestaurantDesc = view.findViewById<TextView>(R.id.tvDescription)
        tvRestaurantDesc.text = requireArguments().getString("restaurantDesc").toString()

        var ivRestaurantImage = view.findViewById<ImageView>(R.id.ivRestaurantImage)
        val image = requireArguments().getString("restaurantImage").toString()

        if(image != "") {
            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.white_image)
                .error(R.drawable.white_image)

            Glide.with(this)
                .load(image)
                .apply(options)
                .into(ivRestaurantImage)
        }else{
            ivRestaurantImage.setImageResource(R.drawable.white_image)
        }

        binding.btBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}