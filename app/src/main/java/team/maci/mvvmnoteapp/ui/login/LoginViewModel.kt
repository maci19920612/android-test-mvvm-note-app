package team.maci.mvvmnoteapp.ui.login

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import team.maci.mvvmnoteapp.manager.AuthService
import team.maci.mvvmnoteapp.manager.NoteManager
import team.maci.mvvmnoteapp.util.UIDispatcher
import timber.log.Timber
import javax.inject.Inject

open class LoginViewModel @Inject constructor(
    private val uiDispatcher: UIDispatcher,
    private val authService: AuthService,
    private val noteManager: NoteManager
): ViewModel(){
    lateinit var navigator: ILoginNavigator


    val username = ObservableField<String>("")
    val password = ObservableField<String>("")
    val loading = ObservableBoolean(false)

    open fun onSubmitAction(){
        GlobalScope.launch(uiDispatcher) {
            val usernameValue = username.get() ?: return@launch
            val passwordValue = password.get() ?: return@launch

            loading.set(true)
            try{
                authService.login(usernameValue, passwordValue)
                noteManager.loadNotes()
                navigator.startListScreen()
            }catch(ex: Exception){
                Timber.e(ex, "Error while we try to login")
            }
            loading.set(false)
        }
    }

    fun onRegisterAction(){
        navigator.startRegistrationScreen()
    }
}