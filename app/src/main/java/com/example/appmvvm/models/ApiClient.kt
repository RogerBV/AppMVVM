package com.example.appmvvm.models

import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class ApiClient {
    companion object {
        val BASE_URL = "http://192.168.1.59"
        private var retrofit: Retrofit? = null

        fun getClient(): Retrofit? {
            if (retrofit == null) {

                val client =
                    OkHttpClient.Builder() // this is my interceptor to set the authorization header
                        .addInterceptor { chain ->
                            val request: Request = chain.request()
                                .newBuilder()
                                .addHeader("content-type","application/json; charset=utf-8")
                                //.addHeader("access-control-allow-origin ", "*")
                                .build()
                            chain.proceed(request)
                        }
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                        .protocols(Arrays.asList( /*Protocol.HTTP_2, */Protocol.HTTP_1_1))
                        .build()


                val interceptor = HttpLoggingInterceptor()
                interceptor.level =  HttpLoggingInterceptor.Level.BODY
                //val client = OkHttpClient.Builder().addInterceptor(interceptor).build()



                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //.client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}