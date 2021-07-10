package com.example.appmvvm.repositories

import com.example.appmvvm.localdatasource.dao.PermitDao
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

    suspend fun ListPermits(): Flow<Result<TrendingPermitResponse>?> {

        return flow {
            emit(fetchTrendingPermitsCached())
            val result = permitRemoteDataSource.ListPermits()
            if(result.isSuccess)
            {
                var data = result.getOrNull()!!
                data.results.let { it ->
                    permitDao.deleteAllPermits(it);
                    permitDao.insertAllPermits(it)
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)

        /*return flow {
            emit(fetchTrendingPermitsCached())
            emit(com.example.appmvvm.Result.loading())
            val result = permitRemoteDataSource.ListPermits()

            //Cache to database if response is successful
            if (result.status == com.example.appmvvm.Result.Status.SUCCESS) {
                result.data?.results?.let { it ->
                    permitDao.deleteAllPermits(it)
                    permitDao.insertAllPermits (it)
                }
            }
            emit(result)
        }.flowOn(Dispatchers.IO)*/
    }
    private fun fetchTrendingPermitsCached(): Result<TrendingPermitResponse>? =
        permitDao.getAllPermits()?.let {
            Result.success(TrendingPermitResponse(it))
        }

}