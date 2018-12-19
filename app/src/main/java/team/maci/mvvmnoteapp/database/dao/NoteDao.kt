package team.maci.mvvmnoteapp.database.dao

import androidx.room.*
import team.maci.mvvmnoteapp.database.entity.NoteEntity


@Dao
interface NoteDao{
    @Query("SELECT * FROM NoteEntity")
    fun getNotes() : List<NoteEntity>

    @Query("SELECT * FROM NoteEntity WHERE id = :id LIMIT 1")
    fun getNoteById(id: Int) : NoteEntity?


    @Delete()
    fun remove(note: NoteEntity)

    @Insert
    fun create(note: NoteEntity)

    @Insert
    fun createAll(notes: List<NoteEntity>)

    @Update
    fun update(note: NoteEntity)


}