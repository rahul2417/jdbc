package com.example.model

import java.sql.Date

data class Bank(
    val id:Int,
    val aadharNumber:Int,
    val aadharCardImage:Byte?,
    val bankName :String,
    val ifscCode:Int,
    val location:String,
    val balance:Float,
    val createdOn:Date,
    val expiresOn:Date,
    val isPremium:Boolean,
    val contact:Int,
    val accountNumber:Int
)