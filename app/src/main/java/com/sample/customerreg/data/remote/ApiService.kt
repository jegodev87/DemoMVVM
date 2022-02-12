package com.sample.customerreg.data.remote

import com.sample.customerreg.models.models.employee.Employee
import com.sample.customerreg.models.request.VerifyUserParams
import com.sample.customerreg.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET(Constants.GET_EMPLOYEE_LIST)
    suspend fun getEmployeeList(): Response<List<Employee>>





}