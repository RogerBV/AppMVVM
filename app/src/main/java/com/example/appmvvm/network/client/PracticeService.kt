package com.example.appmvvm.network.client

import com.example.appmvvm.localdatasource.entities.Permit
import com.example.appmvvm.network.models.RegisteredPermit
import com.example.appmvvm.network.models.TrendingPermitResponse
import retrofit2.Response
import retrofit2.http.GET

interface PracticeService {
    @GET("api/getPermits")
    suspend fun listPermits(): Response<List<Permit>>
}