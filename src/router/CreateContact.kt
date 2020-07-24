package com.example.router

import com.example.model.Contact
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import java.lang.Exception
import java.sql.Connection

fun Route.createContact(conn: Connection?) {
    post("/createcontact") {
        try {
            val contact = call.receive<Contact>()
            val stmt = conn?.createStatement()
            val sql = "INSERT INTO contact(id,personal_number,service_provider_id,service_provider,started_on,expires_on,landline_number,landline_service_id,landline_provider)" +
                    "VALUES (${contact.id},${contact.personalNumber},${contact.serviceProviderId},'${contact.serviceProvider}','${contact.startedOn}','${contact.expiresOn}',${contact.landlineNumber},${contact.landlineServiceId},'${contact.landlineProvider}')"
            stmt?.executeUpdate(sql)
            call.respond(HttpStatusCode.Created,contact)
        }catch (e: Exception){
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}