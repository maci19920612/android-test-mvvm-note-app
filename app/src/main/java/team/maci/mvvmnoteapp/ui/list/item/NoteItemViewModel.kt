package team.maci.mvvmnoteapp.ui.list.item

import android.provider.ContactsContract
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import team.maci.mvvmnoteapp.database.entity.NoteEntity
import team.maci.mvvmnoteapp.model.Note
import team.maci.mvvmnoteapp.ui.list.ListViewModel

class NoteItemViewModel(
    val model: NoteEntity,
    private val parentViewModel: ListViewModel
) : ViewModel() {

    fun onItemClickAction() {
        parentViewModel.onListItemClickAction(model)
    }

    fun onRemoveItemClickAction() {
        parentViewModel.onRemoveItemClickAction(model)
    }
}