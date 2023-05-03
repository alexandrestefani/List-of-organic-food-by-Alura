package com.alexandrestefani.org.Database.DAO

import androidx.room.*
import com.alexandrestefani.org.Model.ProductList
import com.google.android.material.circularreveal.CircularRevealHelper

@Dao

interface DAO_Product_Interface {

    @Query("SELECT*FROM ProductList")
    fun getAllProduct():List<ProductList>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun save(productList: ProductList)

    @Delete
    fun delete(product: ProductList)

    @Update
    fun update(product: ProductList)

    @Query("SELECT * FROM ProductList WHERE id = :id")
    fun buscaPorId(id: Long) : ProductList?
}