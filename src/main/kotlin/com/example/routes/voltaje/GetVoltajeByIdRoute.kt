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
fun Route.getVoltajeByIdRoute(
    voltajeDataSource: VoltajeDataSource
) {
    get("/{id}") {
        try {
            // Extraer el ID de la solicitud
            val id = call.parameters["id"] ?: return@get call.respond(
                status = HttpStatusCode.BadRequest,
                message = ApiResponseError(
                    errorCode = HttpStatusCode.BadRequest.value,
                    message = "El parámetro 'id' es obligatorio"
                )
            )

            // Obtener el voltaje por ID
            val voltaje = voltajeDataSource.getVoltajeByStacion(id)

            if (voltaje != null) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = voltaje
                )
            } else {
                call.respond(
                    status = HttpStatusCode.NotFound,
                    message = ApiResponseError(
                        errorCode = HttpStatusCode.NotFound.value,
                        message = "No se encontró un voltaje con el ID: $id"
                    )
                )
            }
        } catch (e: Exception) {
            call.respond(
                status = HttpStatusCode.InternalServerError,
                message = ApiResponseError(
                    errorCode = HttpStatusCode.InternalServerError.value,
                    message = "Error al obtener el voltaje: ${e.localizedMessage}"
                )
            )
        }
    }
}
