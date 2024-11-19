package com.example.infrastructure.dataSource

import com.example.domain.voltaje.model.Rele
import com.example.domain.voltaje.model.Voltaje
import com.example.domain.voltaje.repocitory.ReleDataSource
import com.example.domain.voltaje.repocitory.VoltajeDataSource
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.CoroutineDatabase
import java.time.LocalDateTime

class ReleDataSourceImpl(
    private val database: CoroutineDatabase
) : ReleDataSource {

    private val reles = database.getCollection<Rele>()

    override suspend fun getRele(): List<Rele?> {
        return reles.find().toList()
    }

    override suspend fun getReleById(id: String): Rele? {
        return reles.findOne(Rele::id eq id)
    }

    override suspend fun getReleByStacion(id: String): Rele? {
        return reles.findOne(Rele::idStacion eq id)
    }

    override suspend fun saveRele(rele: Rele): Boolean {
        return try {
            val result = reles.insertOne(rele)
            result.wasAcknowledged()
        } catch (e: Exception) {
            println("Error al guardar el rele: ${e.message}")
            false
        }
    }


    override suspend fun updateReleStatus(idStacion: String, status: Boolean, updatedAt: String): Boolean {
        val updates = combine(
            setValue(Rele::status, status),
            setValue(Rele::updatedAt, updatedAt),
        )
        return reles.updateOne(
            filter = Rele::idStacion eq idStacion,
            update = updates
        ).wasAcknowledged()
    }
}
