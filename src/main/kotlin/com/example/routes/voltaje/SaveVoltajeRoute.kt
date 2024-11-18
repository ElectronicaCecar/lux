package com.example.routes.voltaje

import com.example.domain.voltaje.model.Voltaje
import com.example.domain.voltaje.repocitory.VoltajeDataSource
import com.kuby.domain.model.ApiResponseError
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDateTime

fun Route.saveEmpresaRoute(
    voltajeDataSource: VoltajeDataSource
) {
    post {
        try {
            val empresaRequest = call.receive<Voltaje>()
            saveEmpresaToDatabase(
                voltajeRequest = empresaRequest,
                voltajeDataSource = voltajeDataSource,
                call = call // Se pasa explícitamente
            )
        } catch (e: Exception) {
            call.respond(
                status = HttpStatusCode.Forbidden,
                message = ApiResponseError(
                    errorCode = HttpStatusCode.Forbidden.value,
                    message = "Error al registrar la Empresa: ${e.localizedMessage}"
                )
            )
        }
    }
}


private fun Voltaje.toModel(): Voltaje = Voltaje(
    nombre = this.nombre,
    idStacion = this.idStacion,
    data = this.data,
    createdAt = LocalDateTime.now().toString()
)


private suspend fun saveEmpresaToDatabase(
    voltajeRequest: Voltaje,
    voltajeDataSource: VoltajeDataSource,
    call: ApplicationCall // Pasamos call explícitamente
) {
    val voltaje = voltajeRequest.toModel()
    val response = voltajeDataSource.saveVoltaje(voltaje)
    if (response) {
        call.respond(
            status = HttpStatusCode.OK,
            message = ApiResponseError(
                errorCode = HttpStatusCode.OK.value,
                message = "Voltaje registrado correctamente"
            )
        )
    } else {
        call.respond(
            status = HttpStatusCode.Conflict,
            message = ApiResponseError(
                errorCode = 403,
                message = "Número de ID ya está en uso."
            )
        )
    }
}
