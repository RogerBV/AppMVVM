package com.example.appmvvm.network.client

import com.example.appmvvm.localdatasource.entities.Permit
import com.example.appmvvm.network.models.RegisteredPermit
import com.example.appmvvm.network.models.Result
import com.example.appmvvm.network.models.TrendingPermitResponse
import com.example.appmvvm.utils.ErrorUtils
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class PermitRemoteDataSource @Inject constructor(private val retrofit:Retrofit) {
    suspend fun ListPermits():Result<List<Permit>>{
        val permitService = retrofit.create(PracticeService::class.java)
        return getResponse(
            request = { permitService.listPermits() },
            defaultErroMessage = "Error fetching permit list"
        )
    }

    private suspend fun <T> getResponse(request:suspend () -> Response<T>, defaultErroMessage:String):Result<T> {
        return try {
            println("I'm working in thread ${Thread.currentThread().name}")
            val result = request.invoke()
            if (result.isSuccessful) {
                return Result.success(result.body())
            } else {
                val errorResponse = ErrorUtils.parseError(result, retrofit)
                Result.error (errorResponse?.status_message ?: defaultErroMessage, errorResponse)
            }
        } catch (e: Throwable) {
            Result.error("Unknown Error", null)
        }
        /*val result = request.invoke()
        if(result.isSuccessful)
        {
            return Result.success(result.body()!!);
        }else{

        }
        return Result.success(result.body()!!);*/
    }
}