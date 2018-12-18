package team.maci.mvvmnoteapp.ui.register

import android.content.Intent
import team.maci.mvvmnoteapp.ui.list.ListActivity
import javax.inject.Inject

interface IRegisterNavigator {
    fun startListScreen()
}

class RegisterNavigator @Inject constructor(
    private val activity: RegisterActivity
) : IRegisterNavigator {
    override fun startListScreen() {
        activity.startActivity(
            Intent(
                activity,
                ListActivity::class.java
            )
        )
    }
}