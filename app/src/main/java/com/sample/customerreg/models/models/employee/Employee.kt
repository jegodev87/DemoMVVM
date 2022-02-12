package com.sample.customerreg.models.models.employee

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "employee")
data class Employee(
    @Embedded val address: Address ? = Address(),
    @Embedded val company: Company ? = Company(),
    val email: String? = "",
    val id: Int? =0,
    val name: String = "",
    val phone: String? = "",
    val profile_image: String? = "",
    val username: String? = "",
    val website: String? = "",
    @PrimaryKey(autoGenerate = true)
    var row_id : Long = 0L,
): Parcelable{
    constructor() : this(Address(), Company(),"",0,"","","","","")
}




