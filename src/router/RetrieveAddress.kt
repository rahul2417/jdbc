package com.example.router

import com.example.model.Address
import com.example.model.Customer
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import java.lang.Exception
import java.sql.Connection

fun Route.retrievAddress(conn: Connection?) {
    get("/retrieveAddress/{id}") {
        try {
            val id = call.parameters["id"]!!.toInt()
            val stmt = conn?.createStatement()
            val sql = "select * from address where id=$id"
            val rs = stmt!!.executeQuery(sql)
            val arrayList = ArrayList<Address>()
            while (rs.next()) {
                val id=rs.getInt("id")
                val buildingNo=rs.getInt("building_no")
                val streetNumber=rs.getInt("street_number")
                val area=rs.getString("area")
                val city=rs.getString("city")
                val pincode=rs.getInt("pincode")
                val country=rs.getString("country")

                val result= Address(
                    id,
                    buildingNo,
                    streetNumber,
                    area,
                    city,
                    pincode,
                    country
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