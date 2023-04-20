package com.alexandrestefani.org.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexandrestefani.org.Model.ProductList
import com.alexandrestefani.org.R
import com.alexandrestefani.org.databinding.CardBinding

class AdapterProductList(produtos: List<ProductList>) : RecyclerView.Adapter<AdapterProductList.MyViewHolder>() {

    private val produtos = produtos.toMutableList()

   /* override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)
        return MyViewHolder(itemView)
    } */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = produtos[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description
        holder.price.text = currentItem.price
    }

    fun refresh(produtos: List<ProductList>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()


    }

    class MyViewHolder(binding: CardBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = itemView.findViewById(R.id.title_card)
        val description : TextView = itemView.findViewById(R.id.description_card)
        val price : TextView = itemView.findViewById(R.id.price_card)

    }
}