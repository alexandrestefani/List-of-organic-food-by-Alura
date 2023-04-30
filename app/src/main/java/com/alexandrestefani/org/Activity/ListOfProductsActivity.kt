package com.alexandrestefani.org.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexandrestefani.org.Adapter.AdapterProductList
import com.alexandrestefani.org.DAO.ProductDAO
import com.alexandrestefani.org.R
import com.alexandrestefani.org.databinding.ActivityLstOfProductBinding

class ListOfProductsActivity : AppCompatActivity() {
    private val dao = ProductDAO()
    private val adapterProduto = AdapterProductList(dao.getAll())
    lateinit var binding: ActivityLstOfProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLstOfProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter_configuration()
        extendfab_configuration()
    }

    override fun onResume() {
        super.onResume()
        adapterProduto.refresh(dao.getAll())
    }

    private fun adapter_configuration() {
        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterProduto

        adapterProduto.onItemClickListener(object: AdapterProductList.onItemClickListener {
            override fun onItemClick(pos: Int) {
                var product = dao.getAll()[pos]
                var positionItem = pos.toString()

                val intentToDetails = Intent(this@ListOfProductsActivity, DetailsActivity::class.java)
                intentToDetails.putExtra("position",positionItem)
                startActivity(intentToDetails)
            }
        })


    }

    private fun extendfab_configuration() {
        val fab = binding.extendedFab
        fab.setOnClickListener {
            goToFormActivity()
        }
    }

    private fun goToFormActivity() {
        val intent = Intent(this, FormsActivity::class.java)
        startActivity(intent)
    }
}