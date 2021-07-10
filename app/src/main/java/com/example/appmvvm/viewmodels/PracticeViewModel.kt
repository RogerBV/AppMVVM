package com.example.appmvvm.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.appmvvm.network.models.RegisteredPermit
import com.example.appmvvm.repositories.PermitRepository

class PracticeViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var permitRepository:PermitRepository

    private lateinit var permitsLiveData: MutableLiveData<List<RegisteredPermit>>

    public suspend fun init()
    {
        //permitRepository = PermitRepository()

        permitRepository.ListPermits()

        //permitsLiveData = permitRepository.getPermitListLiveData()
    }
    public fun ListPermits(): MutableLiveData<List<RegisteredPermit>>
    {
        return permitsLiveData;
    }

}