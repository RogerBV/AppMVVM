package com.example.appmvvm.localdatasource.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.appmvvm.localdatasource.entities.Permit

@Database(entities = [Permit::class],version = 1)
@TypeConverters(GenreConverters::class)
abstract class AppDatabase:RoomDatabase() {
    abstract fun permitDao():PermitDao
    /*companion object{
        private var INSTANCE: AppDatabase? = null;
        fun getInstance(context:Context):AppDatabase{
            if(INSTANCE == null)
            {
                INSTANCE = Room.databaseBuilder(
                    context
                    ,AppDatabase::class.java,"app.db"
                ).allowMainThreadQueries().build()
            }//.fallbackToDestructiveMigration()
            return INSTANCE!!
        }

    }*/
}