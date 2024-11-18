package com.example.domain.voltaje.repocitory

import com.example.domain.voltaje.model.Rele

interface ReleDataSource {
    suspend fun getRele(): List<Rele?>
    suspend fun getReleById(id: String): Rele?
    suspend fun getReleByStacion(id: String): Rele?
    suspend fun saveRele(rele: Rele): Boolean

    // Nuevo método para actualizar `status` y `updatedAt`
    suspend fun updateReleStatus(id: String, status: Boolean, updatedAt: String): Boolean
}


