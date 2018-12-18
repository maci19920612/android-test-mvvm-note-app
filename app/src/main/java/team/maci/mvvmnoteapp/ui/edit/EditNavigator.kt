package team.maci.mvvmnoteapp.ui.edit

import javax.inject.Inject

interface IEditNavigator{
    fun navigateBack()
}

class EditNavigator @Inject constructor(
    private val activity: EditActivity
) : IEditNavigator{
    override fun navigateBack() {
        activity.finish()
    }
}