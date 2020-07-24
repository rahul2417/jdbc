package com.example.router

import com.example.model.Bank
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import java.lang.Exception
import java.sql.Connection

fun Route.createBank(conn: Connection?) {
    post("/createBank") {
        try {
            val bank = call.receive<Bank>()
            val stmt = conn?.createStatement()
            val sql = "INSERT INTO bank (id,aadhar_number,bank_name,ifsc_code,location,balance,created_on,expires_on,isPremium,contact,account_number)" +
                    "VALUES (${bank.id},${bank.aadharNumber},'${bank.bankName}',${bank.ifscCode},'${bank.location}',${bank.balance},'${bank.createdOn}','${bank.expiresOn}',${bank.isPremium},${bank.contact},${bank.accountNumber})"
            stmt?.executeUpdate(sql)
            call.respond(HttpStatusCode.Created,bank)
        }catch (e: Exception){
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}