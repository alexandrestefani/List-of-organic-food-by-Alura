package com.alexandrestefani.org.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.alexandrestefani.org.DAO.ProductDAO
import com.alexandrestefani.org.Extensions.loadImage
import com.alexandrestefani.org.R
import com.alexandrestefani.org.databinding.ActivityDetailsBinding
import java.text.NumberFormat
import java.util.*

class DetailsActivity : AppCompatActivity() {
    private val dao = ProductDAO()
    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        var positionInt: Int = getIntentDatas()
        configurateDetailsView(positionInt)
    }

    private fun getIntentDatas(): Int {
        val dados = intent.extras
        var position = dados?.getString("position")
        var positionInt: Int = position?.toInt() ?: 0
        return positionInt
    }

    private fun configurateDetailsView(positionInt: Int) {
        var product = dao.getAll()[positionInt]

        val priceformated: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        binding.priceDetails.text = priceformated.format(product.price).toString()
        binding.titleTextDetails.text = product.title
        binding.imageDetails.loadImage(product.image)
        binding.textExplamationDetails.text = product.description
    }
}