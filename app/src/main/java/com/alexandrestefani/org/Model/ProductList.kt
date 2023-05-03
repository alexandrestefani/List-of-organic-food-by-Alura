package com.alexandrestefani.org.Model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize

data class ProductList(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    var title: String,
    var description: String,
    var price: Double,
    var image: String? = null
):Parcelable

