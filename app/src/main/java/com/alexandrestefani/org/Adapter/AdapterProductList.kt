package com.alexandrestefani.org.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexandrestefani.org.Extensions.loadImage
import com.alexandrestefani.org.Model.ProductList
import com.alexandrestefani.org.R
import com.alexandrestefani.org.databinding.CardBinding
import java.text.NumberFormat
import java.util.*

class AdapterProductList(
    private val context: Context,
    produtos: List<ProductList> = emptyList()) : RecyclerView.Adapter<AdapterProductList.MyViewHolder>() {

    private val produtos = produtos.toMutableList()
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(pos: Int)
    }
    fun onItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CardBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        ),mListener)
    }

    override fun getItemCount(): Int {
        return produtos.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = produtos[position]
        holder.title.text = currentItem.title
        holder.description.text = currentItem.description

        val priceformated: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt","br"))
        val finalprice: String = priceformated.format(currentItem.price)
        holder.price.text = finalprice
        holder.image.loadImage(currentItem.image)
    }

    fun refresh(produtos: List<ProductList>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

    class MyViewHolder(binding: CardBinding,listener: onItemClickListener) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = itemView.findViewById(R.id.title_card)
        val description : TextView = itemView.findViewById(R.id.description_card)
        val price : TextView = itemView.findViewById(R.id.price_card)
        val image: ImageView = itemView.findViewById(R.id.image_card)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(bindingAdapterPosition)
            }
        }
    }
}