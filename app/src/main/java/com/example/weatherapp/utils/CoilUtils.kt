package com.example.weatherapp.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.weatherapp.R


object CoilUtils {


    @Composable
    fun getAsyncImage(
        model: Any,
        @DrawableRes placeholder: Int = R.drawable.placeholder,
        filterQuality: FilterQuality = FilterQuality.Low,
    ) {
        AsyncImage(
            model = model,
            contentDescription = "Image from URL",
            placeholder = painterResource(id = placeholder),
            error = painterResource(id = placeholder),
            fallback = painterResource(id = placeholder),
            filterQuality = filterQuality
        )
    }
}