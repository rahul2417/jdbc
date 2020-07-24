package com.example

import com.example.router.*
import com.fasterxml.jackson.databind.SerializationFeature
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.routing


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        val conn= initDB()
        createCustomer(conn)
        createBank(conn)
        createContact(conn)
        createAtm(conn)
        createAddress(conn)
        retrieveCustomer(conn)
        retrievBank(conn)
        retrievAtm(conn)
        retrievAddress(conn)
        retrieveContact(conn)
        retrieveCustomerBankDetails(conn)
        retrieveCustomerWithCreditCard(conn)
        retrieveContactsOfCity(conn)
    }
}


