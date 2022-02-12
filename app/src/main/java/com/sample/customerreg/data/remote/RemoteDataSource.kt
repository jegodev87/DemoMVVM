package com.sample.customerreg.data.remote

import com.sample.customerreg.data.base.local.EmployeeDao
import com.sample.customerreg.models.models.employee.Employee
import com.sample.customerreg.models.request.VerifyUserParams
import javax.inject.Inject

class RemoteDataSource @Inject
constructor(private val service: ApiService, private val dao: EmployeeDao
) {

    suspend fun getEmployeeDetails() = service.getEmployeeList()

    suspend fun getCachedEomployes() = dao.getAllEmployees()

    suspend fun saveEmployees(employees : List<Employee>) = dao.saveEmployees(employees)

}