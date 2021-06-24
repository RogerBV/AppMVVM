package com.example.appmvvm.permits

import androidx.lifecycle.MutableLiveData
import com.example.appmvvm.models.ApiClient
import com.example.appmvvm.models.PracticeService
import com.example.appmvvm.models.RegisteredPermit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import kotlin.math.log

class PracticeRepository {
    lateinit var practiceService:PracticeService;
    lateinit var permitsReponseLiveData:MutableLiveData<List<RegisteredPermit>>;

    constructor()
    {
        permitsReponseLiveData = MutableLiveData<List<RegisteredPermit>>()
        //val interceptor = HttpLoggingInterceptor()
        //interceptor.level =  HttpLoggingInterceptor.Level.BODY
        //val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        practiceService = ApiClient.getClient()!!.create(PracticeService::class.java)
    }

    suspend fun ListPermits()
    {
        try
        {
            val call = practiceService.listPermits()
            call.enqueue(object : Callback<List<RegisteredPermit>> {
                override fun onResponse(
                    call: Call<List<RegisteredPermit>>,
                    response: Response<List<RegisteredPermit>>
                ) {
                    if (response.body() != null) {
                        permitsReponseLiveData.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<List<RegisteredPermit>>, t: Throwable) {
                    permitsReponseLiveData.postValue(null)
                }
            })

        }catch (ex:Exception)
        {

        }
    }

    public fun getPermitListLiveData():MutableLiveData<List<RegisteredPermit>>
    {
        return permitsReponseLiveData;
    }
}