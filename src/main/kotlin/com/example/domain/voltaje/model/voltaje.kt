package com.example.domain.voltaje.model

import kotlinx.serialization.Serializable
import org.bson.types.ObjectId

@Serializable
data class Voltaje(
    val id: String = ObjectId().toString(),
    val idStacion: String?=null,
    val nombre: String?=null,
    val data: String?=null,
    val createdAt: String?=null,
)