package com.alexandrestefani.org.Database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alexandrestefani.org.Database.DAO.DAO_Product_Interface
import com.alexandrestefani.org.Model.ProductList

@Database(entities = [ProductList::class], version = 1)
abstract class APPDataBase:RoomDatabase(){
    abstract fun dao_product_interface(): DAO_Product_Interface

    companion object {
        fun instance(context: Context):APPDataBase{
            return  Room.databaseBuilder(
                context,
                APPDataBase::class.java,
                "orgs.db"
            ).allowMainThreadQueries()
                .build()

        }
    }

}