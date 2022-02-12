package com.sample.customerreg.data.base.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.customerreg.models.models.employee.Employee

@Database(
    entities = [Employee::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){

    abstract val dao: EmployeeDao
}
