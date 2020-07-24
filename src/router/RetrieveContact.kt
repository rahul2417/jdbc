package com.example.router

import com.example.model.Contact
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import java.lang.Exception
import java.sql.Connection


fun Route.retrieveContact(conn: Connection?) {
    get("/retrieveContact/{id}") {
        try {
            val id = call.parameters["id"]!!.toInt()
            val stmt = conn?.createStatement()
            val sql = "select * from contact where id=$id"
            val rs = stmt!!.executeQuery(sql)
            val arrayList = ArrayList<Contact>()
            while (rs.next()) {
                val id = rs.getInt("id")
                val personalNumber=rs.getInt("personal_number")
                val serviceProviderId = rs.getInt("service_provider_id")
                val serviceProvider = rs.getString("service_provider")
                val createdOn=rs.getDate("started_on")
                val expiresOn=rs.getDate("expires_on")
                val landlineNumber=rs.getInt("landline_number")
                val landlineServiceId=rs.getInt("landline_service_id")
                val landlineProvider=rs.getString("landline_provider")

                val result= Contact(
                    id,
                    personalNumber,
                    serviceProviderId,
                    serviceProvider,
                    createdOn,
                    expiresOn,
                    landlineNumber,
                    landlineServiceId,
                    landlineProvider
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