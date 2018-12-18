package team.maci.mvvmnoteapp.ui.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import team.maci.mvvmnoteapp.R.attr.adapter
import team.maci.mvvmnoteapp.manager.NoteManager
import team.maci.mvvmnoteapp.model.Note
import team.maci.mvvmnoteapp.ui.list.adapter.NoteAdapter
import team.maci.mvvmnoteapp.util.UIDispatcher
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val uiDispatcher: UIDispatcher,
    private val noteManager: NoteManager
) : ViewModel() {
    val adapter = NoteAdapter(this)
    lateinit var navigator: IListNavigator
    private var listJob: Job? = null

    fun onStart() {
        listJob = GlobalScope.launch(uiDispatcher) {
            val notes = noteManager.getNotes()
            adapter.setItems(notes)
        }
    }

    fun onStop() {
        listJob?.cancel()
        listJob = null
    }

    fun onListItemClickAction(note: Note) {
        navigator.startDetailsScreen(note)
    }

    fun onAddItemClickAction() {
        navigator.startAddNoteScreen()
    }

    fun onRemoveItemClickAction(note: Note) {
        GlobalScope.launch(uiDispatcher) {
            noteManager.deleteNote(note)
            adapter.removeItem {
                it.model == note
            }
        }
    }

}