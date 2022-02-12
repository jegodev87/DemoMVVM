package com.sample.customerreg.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.customerreg.R
import com.sample.customerreg.data.base.Resource
import com.sample.customerreg.databinding.ActivityMainBinding
import com.sample.customerreg.models.models.employee.Employee
import com.sample.customerreg.utils.OnRecyclerCallback
import com.sample.customerreg.utils.printData
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.*
import kotlin.collections.ArrayList


@AndroidEntryPoint
@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModels<EmployeeViewModel>()
    private lateinit var _binding: ActivityMainBinding
    val mEmployeeList = ArrayList<Employee>()
    private lateinit var mEmployeeAdapter: EmployeesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        initRecyclerview()
        fetchAndLoadEmployeeResults()
         searchEmployees()


    }


    fun initRecyclerview() {
        mEmployeeAdapter = EmployeesAdapter(mEmployeeList,object : OnRecyclerCallback{
            override fun callBack(type: Int, position: Int, mData: Any) {
                var mSelected = mData as Employee
               /* var intent = Intent(this@MainActivity, EmployeeDetailsActivity::class.java)
                intent.putExtra("test", mSelected)*/
                startActivity(EmployeeDetailsActivity.instance(this@MainActivity,mSelected))
            }

        })
        _binding.employeeRecyclerview?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mEmployeeAdapter
            mEmployeeAdapter.notifyDataSetChanged()
        }
    }

    private fun searchEmployees() {
        _binding.searchEmployee.addTextChangedListener { chars ->
            if (chars != null && chars.length > 0) {
                var mKeyWord = chars.toString().toLowerCase(Locale.ROOT)
                val mSearchResultList = mEmployeeList.filter {
                    it.name.toLowerCase(Locale.ROOT).startsWith(mKeyWord) or
                            it.name.toLowerCase(
                                Locale.ROOT
                            ).contains(mKeyWord) or   it.company?.name.toString().toLowerCase(Locale.ROOT).startsWith(mKeyWord) or
                            it.company?.name.toString().toLowerCase(
                                Locale.ROOT
                            ).contains(mKeyWord)
                }
                if (mSearchResultList.isEmpty()){
                    _binding.errorText.text = getString(R.string.no_results)
                    employee_recyclerview.visibility = View.GONE
                }else {
                    _binding.errorText.text = null
                    employee_recyclerview.visibility = View.VISIBLE
                }
                mEmployeeAdapter.showSearchResults(mSearchResultList)
            }else{
                mEmployeeAdapter.showSearchResults(mEmployeeList)
            }
        }
    }


    fun fetchAndLoadEmployeeResults() {
        mainViewModel.fetchEmployeeList()
        mainViewModel.response.observe(this) { response ->
            when (response) {
                is Resource.Success -> {
                    response.data?.apply {
                        printData("result -->> " + this)
                        mEmployeeList.clear()
                        mEmployeeList.addAll(this)
                        mEmployeeAdapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error -> {
                    printData(response.data.toString())
                }

                is Resource.Loading -> {

                }
            }
        }
    }


}