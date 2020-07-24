package com.example

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection
import java.util.*
import javax.sql.DataSource

fun initDB(): Connection? {
    val dataSource: DataSource
    val hikariConfig = HikariConfig()
    val properties = Properties()
    properties.setProperty("autosave", "conservative")
    hikariConfig.jdbcUrl =
        "jdbc:postgresql://localhost:5432/customer"
    hikariConfig.username = "rahul"
    hikariConfig.password = "rahul"
    hikariConfig.driverClassName="org.postgresql.Driver"
    dataSource = HikariDataSource(hikariConfig)
    return dataSource.connection

}