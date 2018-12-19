package team.maci.mvvmnoteapp.ui.edit

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import team.maci.mvvmnoteapp.database.entity.NoteEntity
import team.maci.mvvmnoteapp.manager.NoteManager
import timber.log.Timber
import javax.inject.Inject

class EditViewModel @Inject constructor(
    private val noteManager: NoteManager
): ViewModel() {

    val loading = ObservableBoolean(false)
    lateinit var note: NoteEntity
    lateinit var navigator: IEditNavigator


    fun onCreate(noteId: Int){
        GlobalScope.launch {
            val noteEntity = noteManager.getNote(noteId)
            if(noteEntity == null){
                note = NoteEntity()
            }else{
                note = noteEntity
            }
        }
    }


    fun onSubmitClickAction(){
        loading.set(true)

        GlobalScope.launch {
            try{
                if(note.id != 0){
                    noteManager.updateNote(note.id, note.title, note.content)
                }else{
                    noteManager.createNote(note.title, note.content)
                }
                navigator.navigateBack()
            }catch(ex: Exception){
                Timber.e(ex)
            }
        }
    }
}