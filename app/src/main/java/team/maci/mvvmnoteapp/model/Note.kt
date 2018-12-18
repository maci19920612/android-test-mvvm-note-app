package team.maci.mvvmnoteapp.model

import java.io.Serializable
import java.util.*



open class BaseNote(
    val id: Int,
    var title: String,
    var content: String
) : Serializable{
    fun hasId() = id > 0
}

class Note(
    id: Int,
    title: String,
    content: String,
    val createdAt: Date,
    val updatedAt: Date?,
    val user: User
) : BaseNote(id, title, content)