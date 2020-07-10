package com.pouyaheydari.sample.map.android.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

/**
 * This function fetches images from the URL and turns it to the bitmap
 * It is needed as the MarkerOptions only accepts bitmaps as input
 *
 * @param url image's url address
 * @param showOnMap a lambda to show fetched bitmap as a marker
 */
fun getBitmap(context: Context, url: String, showOnMap: (Bitmap) -> Unit) =
    Glide.with(context).asBitmap().load(url).into(object : CustomTarget<Bitmap>() {
        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            showOnMap(resource)
        }

        override fun onLoadCleared(placeholder: Drawable?) {}
    })