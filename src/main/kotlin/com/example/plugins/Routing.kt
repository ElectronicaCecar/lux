package com.example.plugins

import com.example.domain.voltaje.repocitory.ReleDataSource
import com.example.domain.voltaje.repocitory.VoltajeDataSource
import com.example.routes.rele.getReleByIdRoute
import com.example.routes.rele.saveReleRoute
import com.example.routes.rele.updateReleStatusRoute
import com.example.routes.voltaje.getVoltajeByIdRoute
import com.example.routes.voltaje.saveEmpresaRoute
import com.kuby.domain.model.EndPoint
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.java.KoinJavaComponent

fun Application.configureRouting() {
    routing {
        val voltajeDataSource: VoltajeDataSource by KoinJavaComponent.inject(VoltajeDataSource::class.java)
        val releDataSource: ReleDataSource by KoinJavaComponent.inject(ReleDataSource::class.java)

        route(EndPoint.DataVoltaje.path) {
            saveEmpresaRoute(voltajeDataSource)
            getVoltajeByIdRoute(voltajeDataSource)
        }
        route(EndPoint.DataRele.path) {
            saveReleRoute(releDataSource)
            getReleByIdRoute(releDataSource)
            updateReleStatusRoute(releDataSource)
        }
    }
}
