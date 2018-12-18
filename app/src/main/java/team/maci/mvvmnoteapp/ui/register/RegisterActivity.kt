package team.maci.mvvmnoteapp.ui.register

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import team.maci.mvvmnoteapp.R
import team.maci.mvvmnoteapp.databinding.ActivityRegisterBinding
import javax.inject.Inject

class RegisterActivity : FragmentActivity(){
    private lateinit var binding: ActivityRegisterBinding

    @Inject
    lateinit var registerNavigator: IRegisterNavigator


    @Inject
    lateinit var viewModelProvider : ViewModelProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        val viewModel = viewModelProvider.get(RegisterViewModel::class.java)
        viewModel.navigator = registerNavigator

        binding.viewModel = viewModel
    }
}