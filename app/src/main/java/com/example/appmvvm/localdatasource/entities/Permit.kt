package com.example.appmvvm.localdatasource.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Permit")
data class Permit (
    @NonNull
    @PrimaryKey(autoGenerate = true)
    var Id:Int = 0,
    var EmployeeName:String = "",
    var EmployeeSurname:String = ""
)
