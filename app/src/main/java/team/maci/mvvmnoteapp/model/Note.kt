package team.maci.mvvmnoteapp.model

import org.joda.time.DateTime
import team.maci.mvvmnoteapp.database.entity.NoteEntity
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
    val createdAt: DateTime,
    val updatedAt: DateTime?,
    val user: User
) : BaseNote(id, title, content)


object NoteMapper{
    fun mapToNoteEntity(note: Note) : NoteEntity{
        return NoteEntity(
            note.id,
            note.title,
            note.content,
            note.createdAt,
            note.updatedAt,
            note.user.id
        )
    }
}