package com.example.appmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.appmvvm.viewmodels.PracticeViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {
    lateinit var practiceViewModel: PracticeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //fun list CoroutineScope.async { ListPermits() }
        GlobalScope.launch {
            ListPermits()
        }



    }
    suspend fun ListPermits()
    {
        practiceViewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory(application)).get(PracticeViewModel::class.java)
        practiceViewModel.init()
    }
}