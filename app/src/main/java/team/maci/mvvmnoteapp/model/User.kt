package team.maci.mvvmnoteapp.model

import java.io.Serializable
import java.util.*

data class User(
    val id: Int,
    val username: String,
    val createdAt: Date,
    val updatedAt: Date?
) : Serializable