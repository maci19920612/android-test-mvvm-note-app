package team.maci.mvvmnoteapp.model

import org.joda.time.DateTime
import team.maci.mvvmnoteapp.database.entity.UserEntity
import java.io.Serializable
import java.util.*

data class User(
    val id: Int,
    val username: String,
    val createdAt: DateTime,
    val updatedAt: DateTime?
) : Serializable



object UserMapper{
    fun mapToUserEntity(user: User) : UserEntity{
        return UserEntity(
            user.id,
            user.username,
            user.createdAt,
            user.updatedAt
        )
    }
}
