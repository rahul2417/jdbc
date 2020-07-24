package com.example.router

import com.example.model.Atm
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import java.lang.Exception
import java.sql.Connection

fun Route.createAtm(conn: Connection?) {
    post("/createAtm") {
        try {
            val atm = call.receive<Atm>()
            val stmt = conn?.createStatement()
            val sql = "INSERT INTO atm (account_number,atm_id,created_on,expires_on,is_credit_card,atm_pin,cvv,international_use)" +
                    "VALUES (${atm.accountNumber},${atm.atmId},'${atm.createOn}','${atm.expiresOn}','${atm.isCreditCard}','${atm.atmPin}','${atm.cvv}',${atm.internationalUse})"
            stmt?.executeUpdate(sql)
            call.respond(HttpStatusCode.Created,atm)
        }catch (e: Exception){
            call.respond(HttpStatusCode.BadRequest)
        }

    }
}