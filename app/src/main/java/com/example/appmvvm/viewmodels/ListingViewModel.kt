package com.example.appmvvm.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmvvm.localdatasource.entities.Permit
import com.example.appmvvm.repositories.PermitRepository
import com.example.appmvvm.network.models.Result
import com.example.appmvvm.network.models.TrendingPermitResponse
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect


class ListingViewModel @ViewModelInject public constructor(private val permitRepository: PermitRepository) :
    ViewModel() {
    private val _permitList = MutableLiveData<Result<List<Permit>>>()
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
    public fun registerPermit(employeeName:String,employeeSurname:String){
        viewModelScope.launch {
            permitRepository.registerPermit(employeeName,employeeSurname).collect {

            }
        }
    }

}