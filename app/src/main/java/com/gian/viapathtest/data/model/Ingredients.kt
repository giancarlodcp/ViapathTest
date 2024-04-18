package com.gian.viapathtest.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class Ingredients(
    @SerializedName("id") val id: Int,
    @SerializedName("originalName") val title: String,
    @SerializedName("image") val imageUrl: String,
    @SerializedName("amount") val amount: Double
) : Parcelable

