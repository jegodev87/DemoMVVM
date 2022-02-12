package com.sample.customerreg.data.repository

import com.sample.customerreg.data.base.BaseApiResponse
import com.sample.customerreg.data.base.Resource
import com.sample.customerreg.data.base.local.EmployeeDao
import com.sample.customerreg.data.remote.RemoteDataSource
import com.sample.customerreg.models.models.employee.Employee
import com.sample.customerreg.utils.printData
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
@ActivityRetainedScoped
class RemoteRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {

    @InternalCoroutinesApi
    suspend fun getEmployeeList(): Flow<Resource<List<Employee>>> {
        return flow<Resource<List<Employee>>> {
            emit(Resource.Loading())
            val cachedEmployees = remoteDataSource.getCachedEomployes()
            if (cachedEmployees.isNotEmpty() && cachedEmployees.size>0){
                emit(Resource.Success(cachedEmployees))
                printData("Loading from cache")
            }else{
                val remoteData = safeApiCall { remoteDataSource.getEmployeeDetails() }
                emit(remoteData)
                remoteData.data?.let { remoteDataSource.saveEmployees(it) }

            }

        }.flowOn(Dispatchers.IO)
    }


}