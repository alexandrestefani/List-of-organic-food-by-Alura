package com.alexandrestefani.org.Extensions

import android.widget.ImageView
import coil.load
import com.alexandrestefani.org.R

fun ImageView.loadImage(url_new: String? = null){
    load(url_new) {
        fallback(R.drawable.baseline_error_outline_30)
        error(R.drawable.baseline_error_outline_30)
    }
}