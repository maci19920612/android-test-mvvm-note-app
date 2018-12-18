package team.maci.mvvmnoteapp.ui.login

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import team.maci.mvvmnoteapp.R
import team.maci.mvvmnoteapp.databinding.ActivityLoginBinding
import javax.inject.Inject

open class LoginActivity : FragmentActivity(){
    @Inject
    lateinit var viewModelProvider: ViewModelProvider

    @Inject
    lateinit var loginNavigator: LoginNavigator

    protected lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        val viewModel = viewModelProvider.get(LoginViewModel::class.java)
        viewModel.navigator = loginNavigator

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel
    }

}