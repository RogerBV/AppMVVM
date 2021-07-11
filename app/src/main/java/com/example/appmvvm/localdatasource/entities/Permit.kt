package com.example.appmvvm.localdatasource.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Permit")
class Permit {
    @PrimaryKey
    val Id:Int? = null
    val employeeName:String = ""
    val employeeSurname:String = ""
}
