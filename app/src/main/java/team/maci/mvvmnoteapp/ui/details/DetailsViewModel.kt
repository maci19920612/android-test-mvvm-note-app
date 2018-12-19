package team.maci.mvvmnoteapp.ui.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import team.maci.mvvmnoteapp.database.entity.NoteEntity
import team.maci.mvvmnoteapp.manager.NoteManager
import team.maci.mvvmnoteapp.model.Note
import java.util.prefs.NodeChangeEvent
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val noteManager: NoteManager
) : ViewModel() {


    fun onStart(noteId: Int) {
        GlobalScope.launch {
            val noteEntity = noteManager.getNote(noteId)
            if (noteEntity == null) {
                navigator.navigateBack()
            }
            note = noteEntity
        }
    }

    lateinit var navigator: IDetailsNavigator

    var note: NoteEntity? = null


    fun onEditClickAction() {
        val note = this.note
        if (note != null) {
            navigator.startEditScreen(note)
        }
    }


}