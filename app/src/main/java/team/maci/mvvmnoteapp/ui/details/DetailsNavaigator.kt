package team.maci.mvvmnoteapp.ui.details

import android.content.Intent
import team.maci.mvvmnoteapp.model.Note
import team.maci.mvvmnoteapp.ui.edit.EditActivity
import javax.inject.Inject

interface IDetailsNavigator{
    fun startEditScreen(note: Note)
    fun navigateBack()
}


class DetailsNavaigator @Inject constructor(
    private val activity: DetailsActivity
) : IDetailsNavigator{
    override fun startEditScreen(note: Note) {
        activity.startActivity(
            Intent(activity, EditActivity::class.java)
                .putExtra(EditActivity.PARAM_NOTE_ID, note.id)
        )
    }

    override fun navigateBack() {
        activity.finish()
    }
}