package team.maci.mvvmnoteapp.ui.list

import android.content.Intent
import android.provider.ContactsContract
import team.maci.mvvmnoteapp.model.Note
import team.maci.mvvmnoteapp.ui.details.DetailsActivity
import team.maci.mvvmnoteapp.ui.edit.EditActivity
import javax.inject.Inject

interface IListNavigator {
    fun startDetailsScreen(note: Note)
    fun startAddNoteScreen()
}

class ListNavigator @Inject constructor(
    private val activity: ListActivity
): IListNavigator {
    override fun startDetailsScreen(note: Note) {
        activity.startActivity(
            Intent(activity,DetailsActivity::class.java)
                .putExtra(DetailsActivity.PARAM_NOTE_ID, note.id)
        )
    }

    override fun startAddNoteScreen() {
        activity.startActivity(
            Intent(activity, EditActivity::class.java)
        )
    }
}