package com.example.router

import com.example.model.Customer
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import java.lang.Exception
import java.sql.Connection

fun Route.createCustomer(conn: Connection?) {
    post("/createCustomer") {
        try {
            val customer = call.receive<Customer>()
            val stmt = conn?.createStatement()
            val sql = "INSERT INTO customer2 (id,person_id,first_name,middle_name,last_name,dob,email,aadhar_number,voter_id,is_adult,gender,nationality)" +
                    "VALUES (${customer.id},${customer.personId},'${customer.firstName}','${customer.middleName}','${customer.lastName}','${customer.dob}','${customer.email}',${customer.aadharNumber},${customer.voterId},${customer.isAdult},'${customer.gender}','${customer.nationality}'))"
            stmt?.executeUpdate(sql)
            call.respond(HttpStatusCode.Created,customer)
        }catch (e: Exception){
            call.respond(HttpStatusCode.BadRequest,e.localizedMessage)
        }
    }
}