package com.example.routes.rele

import com.example.domain.voltaje.model.Rele
import com.example.domain.voltaje.repocitory.ReleDataSource
import com.kuby.domain.model.ApiResponseError
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDateTime

fun Route.saveReleRoute(
    releDataSource: ReleDataSource
) {
    post{
        try {
            val releRequest = call.receive<Rele>()
            saveEmpresaToDatabase(
                releRequest = releRequest,
                releDataSource = releDataSource,
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


private fun Rele.toModel(): Rele = Rele(
    idStacion = this.idStacion,
    status = this.status,
    updatedAt = LocalDateTime.now().toString()
)


private suspend fun saveEmpresaToDatabase(
    releRequest: Rele,
    releDataSource: ReleDataSource,
    call: ApplicationCall // Pasamos call explícitamente
) {
    val voltaje = releRequest.toModel()
    val response = releDataSource.saveRele(voltaje)
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
