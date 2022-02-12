package com.sample.customerreg.models.models.employee

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Company(
    val bs: String ? = null,
    val catchPhrase: String ? = null,
    @ColumnInfo(name = "company_name")
    var name: String? = "company information unavailable"
):Parcelable{
    constructor() : this("","","company information unavailable")
}