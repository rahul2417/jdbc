package com.example.router

import com.example.model.Atm
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import java.lang.Exception
import java.sql.Connection

fun Route.retrievAtm(conn: Connection?) {
    get("/retrieveAtm/{id}") {
        try {
            val id = call.parameters["id"]!!.toInt()
            val stmt = conn?.createStatement()
            val sql = "select * from Atm where atm_id=$id"
            val rs = stmt!!.executeQuery(sql)
            val arrayList = ArrayList<Atm>()
            while (rs.next()) {
                val accountNumber=rs.getInt("account_number")
                val atmId=rs.getInt("atm_id")
                val createdOn=rs.getDate("created_on").toString()
                val expiresOn=rs.getDate("expires_on").toString()
                val isCreditCard=rs.getBoolean("is_credit_card")
                val atmPin=rs.getInt("atm_pin")
                val cvv=rs.getInt("cvv")
                val internationalUse=rs.getBoolean("international_use")

                val result= Atm(
                    accountNumber,
                    atmId,
                    createdOn,
                    expiresOn,
                    isCreditCard,
                    atmPin,
                    cvv,
                    internationalUse
                )
                arrayList.add(result)
            }
            rs.close()
            call.respond(HttpStatusCode.OK,arrayList)
        }catch (e: Exception){
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}