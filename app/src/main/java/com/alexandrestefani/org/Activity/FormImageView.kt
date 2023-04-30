package com.alexandrestefani.org.Activity

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.alexandrestefani.org.Extensions.loadImage
import com.alexandrestefani.org.databinding.ImportImageBinding

class FormImageDialog(private val context: Context) {
    fun show_it(whenImageUpload:(image:String) ->  Unit){
        val binding = ImportImageBinding.inflate(LayoutInflater.from(context))
        binding.buttonAddImage.setOnClickListener {
            val url = binding.inputImagefield.text.toString()
            binding.imageImageImport.loadImage(url)
        }

        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Confirmar"){ _,_ ->
                val url_image = binding.inputImagefield.text.toString()
                whenImageUpload(url_image)
            }
            .show()
    }
}