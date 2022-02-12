package com.sample.customerreg.models.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterUserParams(
        val Address1: String? = "",
        val Address2: String? = "",
        val Address3: String? = "",
        val CustomerName: String? = "",
        val CustomerRegID: Int? = 0,
        val DistributorName: String? = "",
        val Phone1:String? = "",
        val Phone2:String? = "",
        val Phone3: String? = "",
        val Phone4: String? = "",
        val Phone5: String? = "",
        val MacID : String?  = ""
):Parcelable {
    constructor() : this("","", "", "", 0, "",
            ",", "", "","","","")

}