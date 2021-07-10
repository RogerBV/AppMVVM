package com.example.appmvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appmvvm.network.models.RegisteredPermit

class PermitSearchResultsAdapter:
    RecyclerView.Adapter<PermitSearchResultsAdapter.PermitSearchResultHolder>() {
    private var results: List<RegisteredPermit> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PermitSearchResultHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(com.example.appmvvm.R.layout.permit_item  , parent, false)
        return PermitSearchResultHolder(itemView)
    }

    override fun onBindViewHolder(holder: PermitSearchResultHolder, position: Int) {
        val permit: RegisteredPermit = results[position]

        if (permit.EmployeeName!=null)
        {
            holder.employeeNameTextView.text = permit.EmployeeName
        }

        if(permit.EmployeeSurname != null)
        {
            holder.employeeSurnameTextView.text = permit.EmployeeSurname
        }

    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun setResults(results: List<RegisteredPermit>) {
        this.results = results
        notifyDataSetChanged()
    }

    class PermitSearchResultHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        lateinit var employeeNameTextView: TextView
        lateinit var employeeSurnameTextView: TextView

        init {
            employeeNameTextView = itemView.findViewById(com.example.appmvvm.R.id.permit_employee_name)
            employeeSurnameTextView = itemView.findViewById(com.example.appmvvm.R.id.permit_employee_surname)

        }
    }

}