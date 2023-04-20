package com.alexandrestefani.org.DAO

import com.alexandrestefani.org.Model.ProductList

class ProductDAO {

    fun addproduct(produto:ProductList){
        Companion.produtos.add(produto)

    }

    fun getAll(): List<ProductList> {
        return Companion.produtos.toList()

    }

    companion object {
        private val produtos: MutableList<ProductList> = mutableListOf()
    }
}