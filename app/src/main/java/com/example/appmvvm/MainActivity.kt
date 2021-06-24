package com.example.appmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.appmvvm.viewmodels.PracticeViewModel
import android.os.Handler
class MainActivity : AppCompatActivity() {
    lateinit var practiceViewModel: PracticeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            startActivity(Intent(this,SecondActivity::class.java))
            finish()
        },2000)



    }
}