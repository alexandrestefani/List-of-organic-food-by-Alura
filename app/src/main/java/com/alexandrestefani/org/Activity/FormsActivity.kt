package com.alexandrestefani.org.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexandrestefani.org.Database.APPDataBase
import com.alexandrestefani.org.Extensions.loadImage
import com.alexandrestefani.org.Model.ProductList
import com.alexandrestefani.org.databinding.ActivityFormsBinding

class FormsActivity : AppCompatActivity() {
    lateinit var binding: ActivityFormsBinding
    private var url: String? = null
    private var idProduto = 0L

    private val produtoDaoDatabase by lazy {
        APPDataBase.instance(this).dao_product_interface()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFormsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        saveButtonConfiguration()
        title = "Cadastrar produto"

        val changeImage = binding.imageviewForms
        changeImage.setOnClickListener {
            FormImageDialog(this)
                .show_it{ url_image->
                    url = url_image
                    changeImage.loadImage(url)
            }
        }

        intent.getParcelableExtra<ProductList>(CHAVE_PRODUTO)?.let { produtoCarregado ->
            idProduto = produtoCarregado.id

            fillUpForms(produtoCarregado)
       }
    }

    private fun fillUpForms(produto: ProductList) {
        title = "Alterar produto"
        url = produto.image
        binding.imageviewForms.loadImage(url)
        binding.titleForms.setText(produto.title)
        binding.descriptionForms.setText(produto.description)
        binding.priceForms.setText(produto.price.toString())
    }

    private fun saveButtonConfiguration() {
        val button = binding.button
        button.setOnClickListener {
            val newproduct = newproduct()
            produtoDaoDatabase.save(newproduct)
            finish()
        }
    }

    private fun newproduct(): ProductList {
        val titleTyped = binding.titleForms
        val descriptionTyped = binding.descriptionForms
        val priceTyped = binding.priceForms

        val pricetextText = priceTyped.text.toString()
        val titletext = titleTyped.text.toString()
        val descriptionText = descriptionTyped.text.toString()
        val pricetext: Double = pricetextText.toDouble()

        val newproduct = ProductList(
            id = idProduto,
            title = titletext,
            description = descriptionText,
            price = pricetext,
            image = url
        )
        return newproduct
    }
}