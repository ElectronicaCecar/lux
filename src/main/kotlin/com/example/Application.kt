package com.example

import com.example.plugins.configureCors
import com.example.plugins.configureKoin
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureKoin()       // Inicializa Koin antes de cualquier otra configuración
    configureRouting()    // Configura las rutas
    configureCors()       // Configura CORS
    configureSerialization() // Configura la serialización
}
