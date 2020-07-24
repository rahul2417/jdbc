package com.example.router

import com.example.model.Customer
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import java.lang.Exception
import java.sql.Connection

fun Route.retrieveCustomerWithCreditCard(conn: Connection?) {
    get("/customerWithCreditCard") {
        try {
            val stmt = conn?.createStatement()
            val sql = "select * from customer2 where id in\n" +
                    "(select id from bank where account_number in\n" +
                    "(select account_number from atm where is_credit_card=true));\n"
            val rs=stmt!!.executeQuery(sql)
            val arrayList = ArrayList<Customer>()
            while (rs.next()) {
                val id = rs.getInt("id")
                val personId=rs.getInt("person_id")
                val firstName = rs.getString("first_name")
                val middleName = rs.getString("middle_name")
                val lastName = rs.getString("last_name")
                val dob=rs.getDate("dob")
                val email=rs.getString("email")
                val aadharNumber=rs.getInt("aadhar_number")
                val voterId=rs.getInt("voter_id")
                val isAdult=rs.getBoolean("is_adult")
                val image=rs.getByte("image")
                val gender=rs.getString("gender")
                val nationality=rs.getString("nationality")

                val result=Customer(
                    id,
                    personId,
                    firstName,
                    middleName,
                    lastName,
                    dob,
                    email,
                    aadharNumber,
                    voterId,
                    isAdult,
                    gender,
                    image,
                    nationality,
                    null
                )
                arrayList.add(result)
            }
            call.respond(HttpStatusCode.OK, arrayList)
        }catch (e: Exception){
            call.respond(HttpStatusCode.BadRequest)
        }

    }
}