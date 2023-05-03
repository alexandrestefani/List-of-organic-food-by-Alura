package com.alexandrestefani.org.Activity


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.alexandrestefani.org.Database.APPDataBase
import com.alexandrestefani.org.Extensions.formatToBrazilianCoin
import com.alexandrestefani.org.Extensions.loadImage
import com.alexandrestefani.org.Model.ProductList
import com.alexandrestefani.org.R
import com.alexandrestefani.org.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private var produto: ProductList? = null
    private var produtoId: Long? = null

    private val binding by lazy {
        ActivityDetailsBinding.inflate(layoutInflater)
    }
    private val produtoDao by lazy {
        APPDataBase.instance(this).dao_product_interface()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tentaCarregarProduto()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onResume() {
        super.onResume()
        produtoId?.let { id ->
            produto = produtoDao.buscaPorId(id)
        }
        produto?.let {
            preencheCampos(it)
        } ?: finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            val intent = Intent(this, FormsActivity::class.java)
            when (item.itemId) {
                R.id.edict_item -> {
                    intent.putExtra(CHAVE_PRODUTO,produto)
                    startActivity(intent)
                    true
                }
                R.id.kill_item -> {
                    produto?.let{produtoDao.delete(it)}
                    finish()
                    true
                }
                R.id.add_item -> {
                    startActivity(intent)
                    true
                }else -> false
            }
           return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        intent.getParcelableExtra<ProductList>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            produtoId = produtoCarregado.id
        } ?: finish()
    }

    private fun preencheCampos(produtoCarregado: ProductList) {
        with(binding) {
            imageDetails.loadImage(produtoCarregado.image)
            titleTextDetails.text = produtoCarregado.title
            textExplamationDetails.text = produtoCarregado.description
            priceDetails.text =
                produtoCarregado.price.formatToBrazilianCoin()
        }
    }
}