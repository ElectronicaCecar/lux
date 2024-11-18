package com.example.domain.voltaje.model

import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class Rele(
    val id: String = ObjectId().toString(),
    val idStacion: String?=null,
    val status: Boolean,
    val updatedAt: String?=null,
)