package com.example.router

import com.example.model.Address
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import java.lang.Exception
import java.sql.Connection

fun Route.createAddress(conn: Connection?) {
    post("/createAddress") {
        val address = call.receive<Address>()
        try {
            val stmt = conn?.createStatement()
            val sql = "INSERT INTO address (id,building_no,street_number,area,city,pincode,country)" +
                    "VALUES (${address.id},${address.buildingNo},'${address.streetNumber}','${address.area}','${address.city}','${address.pincode}','${address.country}')"
            stmt?.executeUpdate(sql)
            call.respond(HttpStatusCode.Created,address)
        }catch (e: Exception){
            call.respond(HttpStatusCode.BadRequest)
        }

    }
}