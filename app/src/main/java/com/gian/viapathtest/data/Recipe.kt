package com.gian.viapathtest.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe (
    val id: String,
    val title: String,
    val imageUrl: String,
    //val ingredients: List<String>,
    val instructions: String
) : Parcelable