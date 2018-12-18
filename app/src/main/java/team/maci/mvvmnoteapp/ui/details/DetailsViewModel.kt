package team.maci.mvvmnoteapp.ui.details

import android.provider.ContactsContract
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import team.maci.mvvmnoteapp.manager.NoteManager
import team.maci.mvvmnoteapp.model.Note
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val noteManager: NoteManager
) : ViewModel() {
    fun onStart(noteId: Int) {
        val note = noteManager.getNote(noteId)
        if (note == null) {
            navigator.navigateBack()
            return
        }
        this.note = note
    }

    lateinit var navigator: IDetailsNavigator

    var note: Note? = null


    fun onEditClickAction() {
        val note = this.note
        if (note != null) {
            navigator.startEditScreen(note)
        }
    }


}