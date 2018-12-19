package team.maci.mvvmnoteapp.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import org.joda.time.DateTime
import team.maci.mvvmnoteapp.database.converters.DateConveter
import java.util.*


@Entity()
@TypeConverters(
    DateConveter::class
)
data class UserEntity(
    @PrimaryKey()
    var id: Int,

    @ColumnInfo(name = "username")
    var username: String,

    @ColumnInfo(name = "created_at")
    var createdAt: DateTime,

    @ColumnInfo(name = "updated_at")
    var updatedAt: DateTime?

)