package team.maci.mvvmnoteapp.ui.splash

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import team.maci.mvvmnoteapp.manager.AuthService
import team.maci.mvvmnoteapp.manager.NoteManager
import team.maci.mvvmnoteapp.util.UIDispatcher
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val uiDispatcher: UIDispatcher,
    private val authService: AuthService,
    private val noteManager: NoteManager
) : ViewModel(){


    lateinit var navigator: ISplashNavigator
    val loading: ObservableBoolean = ObservableBoolean(true)


    fun onCreate(){

        GlobalScope.launch(uiDispatcher){
            delay(2000)
            loading.set(false)
            delay(2000)
            noteManager.loadNotes()
            if(authService.isLoggedIn()){
                navigator.startListScreen()
            }else{
                navigator.startLoginScreen()
            }
        }
    }
}
