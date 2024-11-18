package com.kuby.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseError(
    val message: String,
    val errorCode: Int
)

