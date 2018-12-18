package team.maci.mvvmnoteapp.ui.edit

import android.provider.ContactsContract
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import team.maci.mvvmnoteapp.manager.NoteManager
import team.maci.mvvmnoteapp.model.BaseNote
import team.maci.mvvmnoteapp.model.Note
import timber.log.Timber
import javax.inject.Inject

class EditViewModel @Inject constructor(
    private val noteManager: NoteManager
): ViewModel() {

    val loading = ObservableBoolean(false)
    lateinit var note: BaseNote
    lateinit var navigator: IEditNavigator


    fun onCreate(noteId: Int){
        val note = noteManager.getNote(noteId)
        if(note == null){
            this.note = BaseNote(-1, "", "")
        }else{
            this.note = note
        }
    }


    fun onSubmitClickAction(){
        loading.set(true)

        GlobalScope.launch {
            try{
                if(note.hasId()){
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