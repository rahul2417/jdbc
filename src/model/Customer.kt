package com.example.model

import java.util.UUID
import java.sql.Date

data class Customer(
    val id:Int,
    val personId :Int,
    val firstName:String,
    val middleName:String?,
    val lastName:String,
    val dob:Date?,
    val email:String,
    val aadharNumber:Int,
    val voterId:Int,
    val isAdult:Boolean,
    val gender:String,
    val image:Byte?,
    val nationality:String,
    val unitId:UUID?
    )