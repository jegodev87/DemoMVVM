package com.sample.customerreg.data.base.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.customerreg.models.models.employee.Employee

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEmployees(mEmployees: List<Employee>)


    @Query("SELECT * FROM employee")
    fun getAllEmployees(): List<Employee>
}
