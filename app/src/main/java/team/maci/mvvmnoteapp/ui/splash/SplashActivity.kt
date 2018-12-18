package team.maci.mvvmnoteapp.ui.splash

import android.app.Activity
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.Fade
import android.transition.TransitionManager
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.OnRebindCallback
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import kotlinx.coroutines.*
import team.maci.mvvmnoteapp.R
import team.maci.mvvmnoteapp.databinding.ActivitySplashBinding
import team.maci.mvvmnoteapp.util.UIDispatcher
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class SplashActivity : FragmentActivity(){
    @Inject
    lateinit var splashViewModelProvider: ViewModelProvider

    @Inject
    lateinit var splashNavigator: SplashNavigator



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        val binding = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
        binding.viewModel = splashViewModelProvider.get(SplashViewModel::class.java).apply {
            navigator = splashNavigator
        }
        binding.addOnRebindCallback(object: OnRebindCallback<ActivitySplashBinding>(){
            override fun onPreBind(binding: ActivitySplashBinding?): Boolean {

                if(binding != null){
                    val fadeTransition = Fade()
                    TransitionManager.beginDelayedTransition(binding.root as ViewGroup, fadeTransition)
                }
                return super.onPreBind(binding)
            }

        })
        binding.viewModel?.onCreate()
    }
}