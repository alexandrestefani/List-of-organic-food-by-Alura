package com.alexandrestefani.org.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexandrestefani.org.Extensions.formatToBrazilianCoin
import com.alexandrestefani.org.Extensions.loadImage
import com.alexandrestefani.org.Model.ProductList
import com.alexandrestefani.org.databinding.CardBinding

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<ProductList> = emptyList(),
    var quandoClicaNoItem: (produto: ProductList) -> Unit = {}
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList()

    inner class ViewHolder(private val binding: CardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var produto: ProductList

        init {
            itemView.setOnClickListener {
                if (::produto.isInitialized) {
                    quandoClicaNoItem(produto)
                }
            }
        }

        fun vincula(produto: ProductList) {
            this.produto = produto
            val nome = binding.titleCard
            nome.text = produto.title
            val descricao = binding.descriptionCard
            descricao.text = produto.description
            val valor = binding.priceCard
            val valorEmMoeda: String = produto.price
                .formatToBrazilianCoin()
            valor.text = valorEmMoeda

            /*val visibilidade = if (produto.image != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.imageCard.visibility = visibilidade*/

            binding.imageCard.loadImage(produto.image)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = CardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position]
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = produtos.size

    fun atualiza(produtos: List<ProductList>) {
        this.produtos.clear()
        this.produtos.addAll(produtos)
        notifyDataSetChanged()
    }

}
