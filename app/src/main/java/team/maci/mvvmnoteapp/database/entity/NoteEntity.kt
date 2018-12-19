package team.maci.mvvmnoteapp.database.entity

import androidx.room.*
import org.joda.time.DateTime
import team.maci.mvvmnoteapp.database.converters.DateConveter
import team.maci.mvvmnoteapp.database.dao.UserDao
import team.maci.mvvmnoteapp.model.User
import java.util.*


@Entity()
@TypeConverters(
    DateConveter::class
)
data class NoteEntity(
    @PrimaryKey()
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "content")
    var content: String,
    @ColumnInfo(name = "created_at")
    var createdAt: DateTime,

    @ColumnInfo(name = "updated_at")
    var updatedAt: DateTime?,

    @ForeignKey(
        entity = UserEntity::class,
        onDelete = ForeignKey.CASCADE,
        parentColumns = ["id"],
        childColumns = ["userId"]
    )
    var userId: Int,

    @Ignore
    var user: UserEntity? = null
){
    fun loadUser(userDao: UserDao){
        user = userDao.getUserById(userId)
    }

    constructor() : this(0, "", "", DateTime(), null, 0, null)
}