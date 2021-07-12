package com.example.appmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmvvm.adapters.PermitAdapter
import com.example.appmvvm.localdatasource.entities.Permit
import com.example.appmvvm.viewmodels.ListingViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import com.example.appmvvm.network.models.Result
import androidx.lifecycle.Observer
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val list = ArrayList<Permit>()
    private val viewModel by viewModels<ListingViewModel>()
    private lateinit var permitAdapter: PermitAdapter
    private lateinit var rvPermits:RecyclerView
    private lateinit var loading:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        subscribeUi()
        /*Handler().postDelayed({
            startActivity(Intent(this,SecondActivity::class.java))
            finish()
        },2000)*/
    }
    private fun init() {
        rvPermits = findViewById(R.id.rvPermits)
        loading = findViewById(R.id.loading)
        val layoutManager = LinearLayoutManager(this)
        rvPermits.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            rvPermits.context,
            layoutManager.orientation
        )

        rvPermits.addItemDecoration(dividerItemDecoration)
        permitAdapter = PermitAdapter(this, list)
        rvPermits.adapter = permitAdapter
    }
    private fun subscribeUi() {
        viewModel.permitList.observe(this, Observer { result ->

            when (result.status ) {
                Result.Status.SUCCESS -> {
                    result.data?.let { list ->
                        permitAdapter.updateData(list)
                    }
                    loading.visibility = View.GONE
                }

                Result.Status.ERROR -> {
                    result.message?.let {
                        //showError(it)
                    }
                    loading.visibility = View.GONE
                }

                Result.Status.LOADING -> {
                    loading.visibility = View.VISIBLE
                }
            }

        })
    }
}