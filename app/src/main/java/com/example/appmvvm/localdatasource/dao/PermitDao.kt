package com.example.appmvvm.localdatasource.dao

import androidx.room.*
import com.example.appmvvm.localdatasource.entities.Permit

@Dao
interface PermitDao {

    @Query("SELECT * FROM Permit")
    fun getAllPermits() :List<Permit>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPermits(permits:List<Permit>)
    @Delete
    fun deleteAllPermits(permits:List<Permit>)

}