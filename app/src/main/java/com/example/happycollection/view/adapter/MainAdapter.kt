package com.example.happycollection.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.happycollection.R
import com.example.happycollection.Utils
import com.example.happycollection.databinding.AdapterListItemBinding
import com.example.happycollection.model.Product
import com.example.happycollection.view.adapter.MainAdapter.*

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {

    private var list = mutableListOf<Product>()

    fun setList(list: List<Product>) {
        this.list = list.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterListItemBinding.inflate(inflater, parent, false)

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MainViewHolder(private val binding: AdapterListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            Glide.with(itemView.context).load(product.image).into(binding.imageUrl)
            binding.title.text = product.title
            binding.category.text = product.category
            binding.description.text = product.description

            val validPrice = Utils().getValidPrice(product.price)
            if (validPrice == false) {
                binding.price.visibility = View.GONE
            } else {
                binding.price.visibility = View.VISIBLE
                binding.price.text = itemView.context.resources.getString(
                    R.string.product_price,
                    validPrice
                )
            }

            val validCount = Utils().getValidCount(product.rating.count)
            if (validCount == false) {
                binding.ratingBar.visibility = View.GONE
                binding.ratingCount.visibility = View.GONE
            } else {
                binding.ratingBar.visibility = View.VISIBLE
                binding.ratingCount.visibility = View.VISIBLE
                binding.ratingBar.rating = Utils().getRating(product.rating.rate)
                binding.ratingCount.text = itemView.context.resources.getString(
                    R.string.rating_count,
                    validCount
                )
            }
        }
    }
}