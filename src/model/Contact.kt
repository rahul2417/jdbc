package com.example.model

import java.sql.Date

data class Contact(
    val id:Int,
    val personalNumber:Int,
    val serviceProviderId:Int,
    val serviceProvider:String,
    val startedOn:Date,
    val expiresOn:Date,
    val landlineNumber:Int,
    val landlineServiceId:Int,
    val landlineProvider:String
)