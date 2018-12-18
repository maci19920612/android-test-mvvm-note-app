package team.maci.mvvmnoteapp.ui.splash

import android.content.Intent
import team.maci.mvvmnoteapp.ui.list.ListActivity
import team.maci.mvvmnoteapp.ui.login.LoginActivity
import javax.inject.Inject

interface ISplashNavigator{
    fun startLoginScreen()
    fun startListScreen()
}

class SplashNavigator @Inject constructor(
    private val activity: SplashActivity
): ISplashNavigator{
    override fun startLoginScreen() {
        activity.startActivity(
            Intent(activity, LoginActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }

    override fun startListScreen() {
        activity.startActivity(
            Intent(activity, ListActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    }
}