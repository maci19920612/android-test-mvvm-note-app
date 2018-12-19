package team.maci.mvvmnoteapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import team.maci.mvvmnoteapp.ui.splash.ISplashNavigator
import team.maci.mvvmnoteapp.ui.splash.SplashActivity
import team.maci.mvvmnoteapp.ui.splash.SplashNavigator
import team.maci.mvvmnoteapp.util.DaggerViewModelFactory
import team.maci.mvvmnoteapp.util.ViewModelKey
import javax.inject.Singleton


@Module
abstract class LoginBinderModule {
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    @Binds
    abstract fun provideLoginViewModel(loginViewModel: LoginViewModel): ViewModel


    @Binds
    abstract fun provideViewModelFactory(daggerViewModelProvider: DaggerViewModelFactory): ViewModelProvider.Factory
}

@Module
class LoginModule {
    @Provides
    fun provideViewModelProvider(activity: LoginActivity, daggerViewModelProvider: ViewModelProvider.Factory) : ViewModelProvider{
        return ViewModelProviders.of(activity, daggerViewModelProvider)
    }
}
