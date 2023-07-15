package com.sneakersapp.utils

import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sneakersapp.R

fun FloatingActionButton.update(isActive: Boolean) {
    if (isActive) {
        this.imageTintList = ContextCompat.getColorStateList(this.context, R.color.white)
        this.backgroundTintList =
            ContextCompat.getColorStateList(this.context, R.color.light_salmon)
    } else {
        this.imageTintList =
            ContextCompat.getColorStateList(this.context, R.color.light_salmon)
        this.backgroundTintList =
            ContextCompat.getColorStateList(this.context, android.R.color.transparent)
    }
}

fun View.hide(){
    this.visibility = View.GONE
}
fun View.show(){
    this.visibility = View.VISIBLE
}