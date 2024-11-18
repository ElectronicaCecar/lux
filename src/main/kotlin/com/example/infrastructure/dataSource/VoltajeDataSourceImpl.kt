package com.example.infrastructure.dataSource

import com.example.domain.voltaje.model.Voltaje
import com.example.domain.voltaje.repocitory.VoltajeDataSource
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.eq

class VoltajeDataSourceImpl(
    private val database: CoroutineDatabase
) : VoltajeDataSource {

    private val voltajes = database.getCollection<Voltaje>()

    override suspend fun getVoltaje(): List<Voltaje?> {
        return voltajes.find().toList()
    }

    override suspend fun getVoltajeById(id: String): Voltaje? {
        return voltajes.findOne(Voltaje::id eq id)
    }

    override suspend fun getVoltajeByStacion(id: String): List<Voltaje?> {
        return voltajes.find(Voltaje::idStacion eq id).toList()
    }

    override suspend fun saveVoltaje(empresa: Voltaje): Boolean {
        val existingEmpresa = voltajes.findOne(Voltaje::id eq empresa.id)
        return if (existingEmpresa == null) {
            val result = voltajes.insertOne(empresa)
            result.wasAcknowledged()
        } else {
            false
        }
    }


}