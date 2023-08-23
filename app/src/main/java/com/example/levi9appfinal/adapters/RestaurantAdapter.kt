package com.example.levi9appfinal.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.levi9appfinal.R

class RestaurantAdapter(private val mList: List<com.example.levi9appfinal.models.Result>): RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view, this.mListener, mList)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val RestaurantModel = mList[position]


        if(RestaurantModel.images.isNotEmpty()) {
            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.white_image)
                .error(R.drawable.white_image)

            Glide.with(holder.imageView.context)
                .load(RestaurantModel.images[0].source_url)
                .apply(options)
                .into(holder.imageView)

        }else {
            holder.imageView.setImageResource(R.drawable.white_image)
        }
        holder.name.text = RestaurantModel.name
        holder.address.text = RestaurantModel.location_id
        holder.score.text = String.format("%.2f", RestaurantModel.score)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    interface onItemClickListener {
        fun onItemClick(restaurantDetails: com.example.levi9appfinal.models.Result)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        this.mListener = listener
    }

    class ViewHolder(view: View, listener: onItemClickListener, list: List<com.example.levi9appfinal.models.Result>) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.ivRestaurantImage)
        val name: TextView = view.findViewById(R.id.tvRestaurantName)
        val address: TextView = view.findViewById(R.id.tvRestaurantAddress)
        val score: TextView = view.findViewById(R.id.tvScore)

        init {
            view.setOnClickListener {
                listener.onItemClick(list[adapterPosition])
            }
        }
    }
}