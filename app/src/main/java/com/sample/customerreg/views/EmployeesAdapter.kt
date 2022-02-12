package com.sample.customerreg.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.sample.customerreg.R
import com.sample.customerreg.models.models.employee.Employee
import com.sample.customerreg.utils.OnRecyclerCallback
import com.sample.customerreg.utils.printData
import kotlinx.android.synthetic.main.row_item_employee.view.*

class EmployeesAdapter(
    private var mList: List<Employee>,
    private val mListener: OnRecyclerCallback
) : RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item_employee, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val mEmployee = mList[position]
        printData(mEmployee.toString())

        holder.apply {
            loadProfileImage(mEmployee)
            employeeNameTextView.text = mEmployee.name.toString()
            companyNameTextView.text =  mEmployee.company?.name?.let { it }?: "No company details found"

        }

        holder.itemView.setOnClickListener {
            mListener.callBack(1,position,mEmployee)
        }

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    fun showSearchResults(mSearchResultList: List<Employee>) {
      this.mList = mSearchResultList
        notifyDataSetChanged()
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val profileIconImageView: ImageView = itemView.profile_image
        val employeeNameTextView: TextView = itemView.employee_name
        val companyNameTextView: TextView = itemView.company_name


        fun loadProfileImage(data: Employee){
            if (data.profile_image.isNullOrEmpty()){
                profileIconImageView.load(R.drawable.place_holder) {
                    crossfade(true)
                }
            }else{
                profileIconImageView.load(data.profile_image){
                    crossfade(true)
                    placeholder(R.drawable.place_holder)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }
}
