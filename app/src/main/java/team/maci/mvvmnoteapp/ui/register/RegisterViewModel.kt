package team.maci.mvvmnoteapp.ui.register

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import team.maci.mvvmnoteapp.manager.AuthService
import team.maci.mvvmnoteapp.manager.NoteManager
import team.maci.mvvmnoteapp.ui.login.LoginViewModel
import team.maci.mvvmnoteapp.util.UIDispatcher
import timber.log.Timber
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val uiDispatcher: UIDispatcher,
    private val authService: AuthService,
    private val noteManager: NoteManager
) : ViewModel(){

    lateinit var navigator: IRegisterNavigator

    val usernameError = ObservableField<String>()
    val username = ObservableField<String>("")
    val passwordError = ObservableField<String>()
    val password = ObservableField<String>("")
    val password2 = ObservableField<String>("")
    val loading = ObservableBoolean(false)

    fun onSubmitAction(){
        val usernameValue = username.get() ?: return
        val passwordValue = password.get() ?: return
        val password2Value = password2.get() ?: return

        var hasError = false
        if(usernameValue == ""){
            usernameError.set("Invalid username")
            hasError = true
        }else{
            usernameError.set(null)
        }


        if(passwordValue == ""){
            passwordError.set("Password cannot be empty")
            hasError = true
        }else if(passwordValue != password2Value){
            passwordError.set("The two password doesn't match")
            hasError = true
        }else{
            passwordError.set(null)
        }

        if(hasError){
            return
        }

        loading.set(true)
        GlobalScope.launch(uiDispatcher) {
            try{
                authService.register(usernameValue, passwordValue)
                noteManager.loadNotes()
                navigator.startListScreen()
            }catch(ex: Exception){
                Timber.e(ex, "Error while registration")
                usernameError.set("Username or password doesn't match")
            }

            loading.set(false)
        }
    }
}