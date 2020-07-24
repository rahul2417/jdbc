package com.example.router

import com.example.model.Bank
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import java.lang.Exception
import java.sql.Connection


fun Route.retrieveCustomerBankDetails(conn: Connection?) {
    get("/customerBankDetails") {
        try {
            val stmt = conn?.createStatement()
            val sql = "select * from bank inner join customer2 on bank.id=customer2.id"
            val rs=stmt!!.executeQuery(sql)
            val arrayList = ArrayList<Bank>()
            while (rs.next()) {
                val id = rs.getInt("id")
                val aadharNumber = rs.getInt("aadhar_number")
                val aadharCardImage = rs.getByte("aadhar_card_image")
                val bankName = rs.getString("bank_name")
                val ifscCode = rs.getInt("ifsc_code")
                val location = rs.getString("location")
                val balance = rs.getFloat("balance")
                val createdOn = rs.getDate("created_on")
                val expiresOn = rs.getDate("expires_on")
                val isPremium = rs.getBoolean("ispremium")
                val contact = rs.getInt("contact")
                val accountNumber = rs.getInt("account_number")

                val result= Bank(
                    id,
                    aadharNumber,
                    aadharCardImage,
                    bankName,
                    ifscCode,
                    location,
                    balance,
                    createdOn,
                    expiresOn,
                    isPremium,
                    contact,
                    accountNumber
                )

                arrayList.add(result)
            }
            call.respond(HttpStatusCode.OK, arrayList)
        }catch (e: Exception){
            call.respond(HttpStatusCode.BadRequest)
        }
    }
}