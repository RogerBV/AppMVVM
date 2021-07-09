package com.example.appmvvm.models

import retrofit2.Call
import retrofit2.http.GET

interface PracticeService {
    @GET("/api/getPermits")
    fun listPermits(): Call<List<RegisteredPermit>>
}