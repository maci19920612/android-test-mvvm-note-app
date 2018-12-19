package team.maci.mvvmnoteapp.manager

import android.view.ViewTreeObserver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import team.maci.mvvmnoteapp.api.ApiClient
import team.maci.mvvmnoteapp.database.NoteDatabase
import team.maci.mvvmnoteapp.database.dao.NoteDao
import team.maci.mvvmnoteapp.database.dao.UserDao
import team.maci.mvvmnoteapp.database.entity.NoteEntity
import team.maci.mvvmnoteapp.model.BaseNote
import team.maci.mvvmnoteapp.model.NoteMapper
import team.maci.mvvmnoteapp.model.UserMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteManager @Inject constructor(
    private val apiClient: ApiClient,
    private val authService: AuthService,
    private val noteDao: NoteDao,
    private val userDao: UserDao,
    private val noteDatabase: NoteDatabase

) {
    private var loadingJob: Job? = null
    suspend fun getNotes(): List<NoteEntity> {
        loadingJob?.join()

        val notes = GlobalScope.async{
            noteDao.getNotes()
        }.await()

        notes.forEach {
            it.loadUser(userDao)
        }

        return notes
    }


    suspend fun loadNotes() {
        loadingJob = GlobalScope.launch {
            noteDatabase.clearAllTables()

            val loadedNotes = apiClient.getNotes(authService.getToken()).await()

            val users = loadedNotes
                .groupBy { it.user.id }
                .filter { it.value.isNotEmpty() }
                .map { it.value[0].user }
                .map { UserMapper.mapToUserEntity(it) }

            userDao.createAll(users)

            val mappedNotes = loadedNotes.map {
                NoteMapper.mapToNoteEntity(it)
            }
            noteDao.createAll(mappedNotes)
        }
        loadingJob?.join()
    }

    suspend fun getNote(id: Int): NoteEntity? {
        return GlobalScope.async {
            noteDao.getNoteById(id)
        }.await()
    }

    suspend fun createNote(title: String, description: String) {
        val noteRequest = BaseNote(0, title, description)

        val createdNote = this.apiClient.createNote(
            this.authService.getToken(),
            noteRequest
        ).await()

        val targetUser = GlobalScope.async {
            userDao.getUserById(createdNote.user.id)
        }.await()

        if (targetUser == null) {
            GlobalScope.launch {
                userDao.create(UserMapper.mapToUserEntity(createdNote.user))
            }.join()
        }

        val noteEntity = NoteMapper.mapToNoteEntity(createdNote)

        GlobalScope.launch {
            noteDao.create(noteEntity)
        }.join()
    }


    suspend fun updateNote(id: Int, title: String, description: String) {
        val noteRequest = BaseNote(id, title, description)
        val updatedNote = apiClient.updateNote(
            authService.getToken(),
            noteRequest
        ).await()

        val mappedNoteEntity = NoteMapper.mapToNoteEntity(updatedNote)


        GlobalScope.launch {
            noteDao.update(mappedNoteEntity)
        }.join()
    }

    suspend fun deleteNote(note: NoteEntity)     {
        apiClient.deleteNote(
            authService.getToken(),
            note.id
        ).join()

        GlobalScope.launch {
            noteDao.remove(note)
        }.join()
    }


}