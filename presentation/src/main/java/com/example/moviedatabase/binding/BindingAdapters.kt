package com.example.moviedatabase.binding

import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener

@BindingAdapter(value = ["imageUrl", "imageRequestListener"], requireAll = false)
fun ImageView.bindImage(url: String?, listener: RequestListener<Drawable?>?) {
    Glide.with(context)
        .load(url)
        .listener(listener)
        .into(this)
}

@BindingAdapter("safeClick")
fun View.safeClick(listener: View.OnClickListener?) {
    val blockInMillis: Long = 500
    var lastClickTime: Long = 0
    this.setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < blockInMillis) {
            return@setOnClickListener
        }
        lastClickTime = SystemClock.elapsedRealtime()
        listener?.onClick(this)
    }
}
