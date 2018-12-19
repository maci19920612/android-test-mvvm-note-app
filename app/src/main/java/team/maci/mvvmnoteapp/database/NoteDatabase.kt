package team.maci.mvvmnoteapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import team.maci.mvvmnoteapp.database.dao.NoteDao
import team.maci.mvvmnoteapp.database.dao.UserDao
import team.maci.mvvmnoteapp.database.entity.NoteEntity
import team.maci.mvvmnoteapp.database.entity.UserEntity


@Database(
    entities = [
        NoteEntity::class,
        UserEntity::class
    ],
    version = 1
)
abstract class NoteDatabase : RoomDatabase(){
    abstract fun noteDao() : NoteDao
    abstract fun userDao() : UserDao
}