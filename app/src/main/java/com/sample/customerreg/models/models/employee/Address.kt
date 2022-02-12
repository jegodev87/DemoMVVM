package com.sample.customerreg.models.models.employee

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address(
    val city: String? = "",
    @Embedded val geo: Geo = Geo(),
    val street: String? = "",
    val suite: String? = "",
    val zipcode: String? = ""
):Parcelable