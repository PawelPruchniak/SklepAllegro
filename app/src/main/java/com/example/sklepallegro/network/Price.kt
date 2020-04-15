package com.example.sklepallegro.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Price(
    val amount: Double,
    val currency: String
) : Parcelable