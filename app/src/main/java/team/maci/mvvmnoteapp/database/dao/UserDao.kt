package team.maci.mvvmnoteapp.database.dao

import androidx.room.*
import team.maci.mvvmnoteapp.database.entity.UserEntity
import javax.inject.Inject

@Dao
interface UserDao {
    @Query("SELECT * FROM UserEntity WHERE id = :id LIMIT 1")
    fun getUserById(id: Int) : UserEntity?

    @Insert
    fun create(insert: UserEntity)

    @Insert
    fun createAll(users: List<UserEntity>)

    @Update
    fun update(user: UserEntity)

}