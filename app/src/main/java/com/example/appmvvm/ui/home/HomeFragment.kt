package com.example.appmvvm.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmvvm.R
import com.example.appmvvm.adapters.PermitAdapter
import com.example.appmvvm.databinding.FragmentHomeBinding
import com.example.appmvvm.localdatasource.entities.Permit
import com.example.appmvvm.network.models.Result
import com.example.appmvvm.viewmodels.ListingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val list = ArrayList<Permit>()
    private val viewModel by viewModels<ListingViewModel>()
    private lateinit var permitAdapter: PermitAdapter
    private lateinit var rvPermits: RecyclerView
    private lateinit var loading: ProgressBar

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        init(root!!)
        subscribeUi()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun init(root:View) {
        rvPermits = root.findViewById(R.id.rvPermits)
        loading = root.findViewById(R.id.loading)
        val layoutManager = LinearLayoutManager(context)
        rvPermits.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            rvPermits.context,
            layoutManager.orientation
        )

        rvPermits.addItemDecoration(dividerItemDecoration)
        permitAdapter = PermitAdapter(requireContext(), list)
        rvPermits.adapter = permitAdapter
    }
    @SuppressLint("FragmentLiveDataObserve")
    private fun subscribeUi() {
        viewModel.permitList.observe(this, Observer { result ->

            when (result.status ) {
                Result.Status.SUCCESS -> {
                    result.data?.let { list ->
                        permitAdapter.updateData(list)
                        rvPermits.adapter = permitAdapter

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