package com.sample.customerreg.data.base

import com.sample.customerreg.utils.printData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

abstract class BaseApiResponse {

    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return Resource.Success(body)
                }
            }else{
                val body = response.errorBody()
                val errorResponse = response.errorBody()?.string()
                printData(errorResponse.toString())
                return error("${""} ${errorResponse?.toString()}")
            }
            return error("${""} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): Resource<T> =
        //NetworkResult.Error("NetLink Cutomer Error : $errorMessage")
    Resource.Error("Failed : $errorMessage")

}