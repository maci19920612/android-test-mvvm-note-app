package team.maci.mvvmnoteapp.model

import team.maci.mvvmnoteapp.database.entity.UserEntity
import java.io.Serializable
import java.util.*

data class User(
    val id: Int,
    val username: String,
    val createdAt: Date,
    val updatedAt: Date?
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
