package com.pouyaheydari.sample.map.android.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.squareup.picasso.Picasso

/**
 * This function fetches images from the URL and turns it to the bitmap
 * It is needed as the MarkerOptions only accepts bitmaps as input
 *
 * @param url image's url address
 * @param showOnMap a lambda to show fetched bitmap as a marker
 */
fun getBitmap(url: String, showOnMap: (Bitmap) -> Unit) =
    Picasso.get().load(url).into(object : com.squareup.picasso.Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            bitmap?.let { showOnMap(it) }
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}
    })
