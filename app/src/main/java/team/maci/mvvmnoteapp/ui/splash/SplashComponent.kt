package team.maci.mvvmnoteapp.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import team.maci.mvvmnoteapp.util.DaggerViewModelFactory
import team.maci.mvvmnoteapp.util.ViewModelKey
import javax.inject.Singleton


@Module
abstract class SplashBinderModule {
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    @Binds
    abstract fun provideViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    abstract fun provideViewModelFactory(factory: DaggerViewModelFactory) : ViewModelProvider.Factory
}

@Module
class SplashModule {

    @Provides
    fun provideViewModelProvider(splashActivity: SplashActivity, viewModelFactory: ViewModelProvider.Factory) : ViewModelProvider{
        return ViewModelProviders.of(splashActivity, viewModelFactory)
    }

    @Provides
    fun provideNavigator(activity: SplashActivity) : ISplashNavigator{
        return SplashNavigator(activity)
    }
}

@Subcomponent(
    modules = [
        SplashModule::class,
        SplashBinderModule::class
    ]
)
interface SplashComponent : AndroidInjector<SplashActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<SplashActivity>()
}