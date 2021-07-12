package com.example.appmvvm.repositories

import com.example.appmvvm.localdatasource.dao.PermitDao
import com.example.appmvvm.localdatasource.entities.Permit
import com.example.appmvvm.network.client.PermitRemoteDataSource
import com.example.appmvvm.network.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PermitRepository @Inject constructor(
    private val permitRemoteDataSource: PermitRemoteDataSource,
    private val permitDao: PermitDao
)
{
    suspend fun registerPermit(employeeName:String,employeeSurname:String):Flow<Result<Permit>?>{
        return flow {
            emit(Result.loading())
            val result = permitRemoteDataSource.registerPermit(employeeName,employeeSurname)
            if(result.status == Result.Status.SUCCESS)
            {
                permitDao.insertPermit(result.data!!)
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    suspend fun ListPermits(): Flow<Result<List<Permit>>?> {

        return flow {
            emit(fetchTrendingPermitsCached())
            emit(Result.loading())
            val result = permitRemoteDataSource.ListPermits()
            if(result.status == Result.Status.SUCCESS)
            {
                result.data?.let { it ->
                //result.data?.results?.let { it ->
                    permitDao.deleteAllPermits(it);
                    permitDao.insertAllPermits(it)
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)

    }
    private fun fetchTrendingPermitsCached(): Result<List<Permit>>? =
        permitDao.getAllPermits()?.let {
            Result.success(it)
        }

}