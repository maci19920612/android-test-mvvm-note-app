package team.maci.mvvmnoteapp.ui.login

import android.app.Activity
import android.content.Intent
import team.maci.mvvmnoteapp.ui.list.ListActivity
import team.maci.mvvmnoteapp.ui.register.RegisterActivity
import javax.inject.Inject

interface ILoginNavigator{
    fun startRegistrationScreen()
    fun startListScreen()
}


class LoginNavigator @Inject constructor(
    private val activity: LoginActivity
): ILoginNavigator{
    override fun startRegistrationScreen() {
        activity.startActivity(
            Intent(activity, RegisterActivity::class.java)
        )
    }

    override fun startListScreen() {
        activity.startActivity(
            Intent(
                activity,
                ListActivity::class.java
            )
        )
    }
}