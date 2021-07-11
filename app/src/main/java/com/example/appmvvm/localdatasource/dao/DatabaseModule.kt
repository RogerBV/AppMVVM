package com.example.appmvvm.localdatasource.dao

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appcontext:Context):AppDatabase
    {
        return Room.databaseBuilder(appcontext
            ,AppDatabase::class.java
            ,"app.db"
        ).build()
    }

    @Provides
    fun providePermitDao(appDatabase:AppDatabase):PermitDao{
        return appDatabase.permitDao()
    }
}