package com.example.appmvvm.models

import retrofit2.Call
import retrofit2.http.GET

interface PracticeService {
    @GET("/Permit/List")
    fun listPermits(): Call<List<RegisteredPermit>>
}