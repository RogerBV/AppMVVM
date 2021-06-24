package com.example.appmvvm.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.appmvvm.models.RegisteredPermit
import com.example.appmvvm.permits.PracticeRepository

class PracticeViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var practiceRepository:PracticeRepository

    private lateinit var permitsLiveData: MutableLiveData<List<RegisteredPermit>>

    public suspend fun init()
    {
        practiceRepository = PracticeRepository()

        practiceRepository.ListPermits()

        permitsLiveData = practiceRepository.getPermitListLiveData()
    }
    public fun ListPermits(): MutableLiveData<List<RegisteredPermit>>
    {
        return permitsLiveData;
    }

}