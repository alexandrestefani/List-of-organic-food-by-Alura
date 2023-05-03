package com.alexandrestefani.org.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexandrestefani.org.Adapter.ListaProdutosAdapter
import com.alexandrestefani.org.Database.APPDataBase
import com.alexandrestefani.org.databinding.ActivityLstOfProductBinding

class ListOfProductsActivity : AppCompatActivity() {


    private val adapterProduto = ListaProdutosAdapter(this)
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
        val db = APPDataBase.instance(this)
        val produtoDaoDatabase = db.dao_product_interface()
        adapterProduto.atualiza(produtoDaoDatabase.getAllProduct())
    }

    private fun adapter_configuration() {
        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterProduto

        adapterProduto.quandoClicaNoItem = {
            val intent = Intent(
                this,
                DetailsActivity::class.java
            ).apply {
                putExtra(CHAVE_PRODUTO, it)
                Log.i("test","teste : $it")
            }
            startActivity(intent)
        }
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