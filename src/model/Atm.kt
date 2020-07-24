package com.example.model

data class Atm(
    val accountNumber:Int,
    val atmId:Int,
    val createOn:String,
    val expiresOn:String,
    val isCreditCard:Boolean,
    val atmPin:Int,
    val cvv:Int,
    val internationalUse:Boolean
)