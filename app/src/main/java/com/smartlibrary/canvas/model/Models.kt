package com.smartlibrary.canvas.model

import androidx.annotation.DrawableRes

data class Book(
    val title: String,
    val author: String,
    val rating: String,
    val tag: String,
    val cover: String,
    @DrawableRes val localCoverRes: Int
)

data class Borrowing(
    val title: String,
    val author: String,
    val due: String,
    val state: String,
    val cover: String,
    @DrawableRes val localCoverRes: Int
)

data class ProfileState(
    val name: String,
    val photo: String
)

data class QuickAction(
    val title: String,
    val subtitle: String,
    val icon: String
)
