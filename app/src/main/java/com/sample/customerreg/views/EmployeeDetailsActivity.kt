package com.sample.customerreg.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import coil.transform.CircleCropTransformation
import com.sample.customerreg.R
import com.sample.customerreg.databinding.AcitivtyEmployeeDetailsBinding
import com.sample.customerreg.databinding.ActivityMainBinding
import com.sample.customerreg.models.models.employee.Employee
import com.sample.customerreg.utils.printData
import java.lang.Exception

class EmployeeDetailsActivity : AppCompatActivity() {
    private lateinit var _binding: AcitivtyEmployeeDetailsBinding
    private var mSelectedEmployeeDetails:  Employee? = null


    companion object{
        const val KEY_EMPLOYEE_DETAILS = "employee_details"
        fun instance(mContext: Context,employeeData : Employee): Intent {
            val mIntent = Intent(mContext, EmployeeDetailsActivity::class.java)
            mIntent.putExtra(KEY_EMPLOYEE_DETAILS,employeeData)
            return mIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = AcitivtyEmployeeDetailsBinding.inflate(layoutInflater)
        setContentView(_binding.root)

      if (intent.hasExtra(KEY_EMPLOYEE_DETAILS)){
          mSelectedEmployeeDetails = intent.getParcelableExtra(KEY_EMPLOYEE_DETAILS)
          printData("Selected employee -->> "+ mSelectedEmployeeDetails)
          loadEmployeeDetails()
      }

    }

    private fun loadEmployeeDetails(){
        mSelectedEmployeeDetails?.apply {


            if (this.profile_image.isNullOrEmpty()){
                _binding.appCompatImageView.load(R.drawable.place_holder) {
                    crossfade(true)
                }
            }else{
                _binding.appCompatImageView.load(this.profile_image){
                    crossfade(true)
                    placeholder(R.drawable.place_holder)
                    transformations(CircleCropTransformation())
                }
            }

            _binding.name.text = this.name
            _binding.username.text = this.username
            _binding.email.text = this.email
            _binding.address.text = this.address?.city.plus(" ").plus(address?.street).plus(" ").plus(address?.suite).plus(" ").plus(address?.zipcode)
            _binding.phone.text = this.phone.toString()
            _binding.website.text = this.website
            _binding.companyDetails.text = this.company?.name


        }
    }
}