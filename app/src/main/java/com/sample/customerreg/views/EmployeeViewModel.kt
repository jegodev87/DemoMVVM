package com.sample.customerreg.views

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.customerreg.data.base.Resource
import com.sample.customerreg.data.repository.RemoteRepository
import com.sample.customerreg.models.models.employee.Employee
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
@InternalCoroutinesApi
class EmployeeViewModel @Inject constructor(
    private val repository: RemoteRepository,
) : ViewModel() {

    private   val _response: MutableLiveData<Resource<List<Employee>>> = MutableLiveData()
    val response: LiveData<Resource<List<Employee>>> = _response



    fun fetchEmployeeList() = viewModelScope.launch {
        repository.getEmployeeList().collect {
            _response.value = it
        }
    }


}