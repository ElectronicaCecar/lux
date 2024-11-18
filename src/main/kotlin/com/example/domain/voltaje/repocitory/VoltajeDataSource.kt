package com.example.domain.voltaje.repocitory

import com.example.domain.voltaje.model.Voltaje

interface VoltajeDataSource {
    suspend fun getVoltaje(): List<Voltaje?>
    suspend fun getVoltajeById(id: String): Voltaje?
    suspend fun getVoltajeByStacion(id: String): List<Voltaje?>
    suspend fun saveVoltaje(empresa: Voltaje): Boolean
}

