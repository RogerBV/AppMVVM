package com.example.appmvvm.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmvvm.network.models.TrendingPermitResponse
import com.example.appmvvm.repositories.PermitRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListingViewModel @ViewModelInject constructor(private val permitRepository: PermitRepository) :
    ViewModel() {
        private val _permitList = MutableLiveData<Result<TrendingPermitResponse>>()
    val permitList = _permitList

    init {
        fetchPermits()
    }

    private fun fetchPermits(){
        viewModelScope.launch {
            permitRepository.ListPermits().collect {
                _permitList.value = it
            }
        }

    }

}