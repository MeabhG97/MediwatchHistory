package com.example.mediwatchhistoryview.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class History(
    @SerialName(value = "taken")
    val taken: Boolean,
    @SerialName(value = "datetime")
    val dateTime: String,
    @SerialName(value = "medications")
    val medications: List<String>
)
