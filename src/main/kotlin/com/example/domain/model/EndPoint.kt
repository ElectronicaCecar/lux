package com.kuby.domain.model

sealed class EndPoint (val path: String) {


    data object DataVoltaje: EndPoint(path = "/api/v1/voltaje")
    data object DataRele: EndPoint(path = "/api/v1/rele")

}