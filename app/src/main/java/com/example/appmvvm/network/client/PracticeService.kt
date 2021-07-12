package com.example.appmvvm.network.client

import com.example.appmvvm.localdatasource.entities.Permit
import com.example.appmvvm.network.models.RegisteredPermit
import com.example.appmvvm.network.models.TrendingPermitResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PracticeService {
    @GET("api/getPermits")
    suspend fun listPermits(): Response<List<Permit>>
    @POST("api/registerPermit")
    suspend fun registerPermit(@Query("employeeName") employeeName:String, @Query("employeeSurname") employeeSurname:String ):Response<Permit>
}