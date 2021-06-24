package com.example.appmvvm.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisteredPermit (
    @SerializedName("Id")
    @Expose
    var Id: Number = 0,

    @SerializedName("EmployeeName")
    @Expose
    var EmployeeName: String = "",

    @SerializedName("EmployeeSurname")
    @Expose
    var EmployeeSurname: String = "",

    @SerializedName("PermitTypeId")
    @Expose
    var PermitTypeId: Number = 0,

    @SerializedName("PermitDate")
    @Expose
    var PermitDate: String = ""
)