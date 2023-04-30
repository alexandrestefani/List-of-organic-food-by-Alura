package com.alexandrestefani.org.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.alexandrestefani.org.DAO.ProductDAO
import com.alexandrestefani.org.Extensions.loadImage
import com.alexandrestefani.org.Model.ProductList
import com.alexandrestefani.org.R
import com.alexandrestefani.org.databinding.ActivityFormsBinding
import com.alexandrestefani.org.databinding.ImportImageBinding
import java.text.NumberFormat
import java.util.*

class FormsActivity : AppCompatActivity() {
    lateinit var binding: ActivityFormsBinding
    private var url: String? = null

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
    }
    private fun saveButtonConfiguration() {
        val button = binding.button
        val dao = ProductDAO()

        button.setOnClickListener {
            val titleTyped = binding.titleForms
            val descriptionTyped = binding.descriptionForms
            val priceTyped = binding.priceForms

            val pricetextText = priceTyped.text.toString()
            val titletext = titleTyped.text.toString()
            val descriptionText = descriptionTyped.text.toString()
            val pricetext: Double = pricetextText.toDouble()

            ConfigurateProduct(titletext, descriptionText, pricetext, dao)
            finish()
        }
    }

    private fun field_validation_is_empty(
        pricetext: Double,
        titletext: String,
        descriptionText: String,
        dao: ProductDAO
    ) {
        if (pricetext == 0.0 || titletext == "" || descriptionText == "") {
            Toast.makeText(this, "Dados não preenchidos", Toast.LENGTH_LONG).show()

        } else {

            ConfigurateProduct(titletext, descriptionText, pricetext, dao)
            finish()
        }
    }

    private fun ConfigurateProduct(
        titletext: String,
        descriptionText: String,
        pricetext: Double,
        dao: ProductDAO
    ) {
        val newproduct = ProductList(
            title = titletext,
            description = descriptionText,
            price = pricetext,
            image = url
        )

        dao.addproduct(newproduct)
    }
}