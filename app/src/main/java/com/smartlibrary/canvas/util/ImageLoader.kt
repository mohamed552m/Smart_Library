package com.smartlibrary.canvas.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.smartlibrary.canvas.R
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String?, @DrawableRes fallbackRes: Int = R.drawable.bg_image_placeholder) {
    if (url.isNullOrBlank()) {
        setImageResource(fallbackRes)
        return
    }

    Picasso.get()
        .load(url)
        .placeholder(fallbackRes)
        .error(fallbackRes)
        .noFade()
        .into(this)
}
