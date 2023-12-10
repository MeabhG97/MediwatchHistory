package com.example.mediwatchhistoryview.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class History(
    val id: String,
    val taken: Boolean,
    val dateTime: String,
    val medications: Array<String>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as History

        if (id != other.id) return false
        if (taken != other.taken) return false
        if (dateTime != other.dateTime) return false
        return medications.contentEquals(other.medications)
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + taken.hashCode()
        result = 31 * result + dateTime.hashCode()
        result = 31 * result + medications.contentHashCode()
        return result
    }
}
