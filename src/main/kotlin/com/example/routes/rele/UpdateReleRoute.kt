package com.example.routes.rele

import com.example.domain.voltaje.repocitory.ReleDataSource
import com.example.domain.voltaje.model.Rele
import com.kuby.domain.model.ApiResponseError
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDateTime

fun Route.updateReleStatusRoute(
    releDataSource: ReleDataSource
) {
    post("/update") { // Ruta para actualizar el estado del relé
        try {
            // Obtener datos de la solicitud
            val releRequest = call.receive<Rele>()

            // Actualizar estado del relé en la base de datos
            val updatedAt = LocalDateTime.now().toString()
            val isUpdated = releRequest.idStacion?.let {
                releDataSource.updateReleStatus(
                    id = it,
                    status = releRequest.status,
                    updatedAt = updatedAt
                )
            }

            // Responder según el resultado
            if (isUpdated == true) {
                call.respond(
                    status = HttpStatusCode.OK,
                    message = ApiResponseError(
                        errorCode = HttpStatusCode.OK.value,
                        message = "Estado del relé actualizado correctamente."
                    )
                )
            } else {
                call.respond(
                    status = HttpStatusCode.Conflict,
                    message = ApiResponseError(
                        errorCode = HttpStatusCode.Conflict.value,
                        message = "No se encontró un relé con el ID proporcionado."
                    )
                )
            }
        } catch (e: Exception) {
            call.respond(
                status = HttpStatusCode.InternalServerError,
                message = ApiResponseError(
                    errorCode = HttpStatusCode.InternalServerError.value,
                    message = "Error al actualizar el estado del relé: ${e.localizedMessage}"
                )
            )
        }
    }
}
