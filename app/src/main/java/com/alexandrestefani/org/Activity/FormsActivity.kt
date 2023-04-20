package com.alexandrestefani.org.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alexandrestefani.org.DAO.ProductDAO
import com.alexandrestefani.org.Model.ProductList
import com.alexandrestefani.org.databinding.ActivityFormsBinding

class FormsActivity : AppCompatActivity() {
    lateinit var binding: ActivityFormsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFormsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        saveButtonConfiguration()


    }

    private fun saveButtonConfiguration() {
        val button = binding.button
        val dao = ProductDAO()

        button.setOnClickListener {
            val titleTyped = binding.titleForms
            val descriptionTyped = binding.descriptionForms
            val priceTyped = binding.priceForms

            val pricetext = priceTyped.text.toString()
            val titletext = titleTyped.text.toString()
            val descriptionText = descriptionTyped.text.toString()

            field_validation_is_empty(pricetext, titletext, descriptionText, dao)


        }
    }

    private fun field_validation_is_empty(
        pricetext: String,
        titletext: String,
        descriptionText: String,
        dao: ProductDAO
    ) {
        if (pricetext == "" || titletext == "" || descriptionText == "") {
            Toast.makeText(this, "Dados não preenchidos", Toast.LENGTH_LONG).show()

        } else {

            ConfigurateProduct(titletext, descriptionText, pricetext, dao)
            finish()
        }
    }

    private fun ConfigurateProduct(
        titletext: String,
        descriptionText: String,
        pricetext: String,
        dao: ProductDAO
    ) {
        val newproduct = ProductList(
            title = titletext,
            description = descriptionText,
            price = pricetext
        )

        dao.addproduct(newproduct)
        Log.i("formulario", "onCreate " + dao.getAll())
    }
}