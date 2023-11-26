package com.him.githubrepositories.core.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.him.githubrepositories.R

fun ImageView.loadImage(url: String, context: Context) {

    val options = RequestOptions().placeholder(createPlaceHolder(context))
        .error(R.drawable.ic_launcher_foreground)

    Glide
        .with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

private fun createPlaceHolder(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

fun View.hide() {
    this.visibility = View.GONE
}