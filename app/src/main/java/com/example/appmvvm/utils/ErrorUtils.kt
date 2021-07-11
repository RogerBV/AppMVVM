package com.example.appmvvm.utils

import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import com.example.appmvvm.network.models.Error;

object ErrorUtils {

    fun parseError(response: Response<*>, retrofit: Retrofit): Error? {
        val converter = retrofit.responseBodyConverter<Error>(Error::class.java, arrayOfNulls(0))
        return try {
            converter.convert(response.errorBody()!!)
        } catch (e: IOException) {
            Error()
        }
    }
}