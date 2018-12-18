package team.maci.mvvmnoteapp.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.*
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import team.maci.mvvmnoteapp.ui.login.LoginModule_ProvideViewModelProviderFactory
import team.maci.mvvmnoteapp.util.DaggerViewModelFactory
import team.maci.mvvmnoteapp.util.DaggerViewModelFactory_Factory
import team.maci.mvvmnoteapp.util.ViewModelKey

@Module
abstract class RegisterBinderModule {
    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

    @Binds
    abstract fun bindNavigator(registerNavigator: RegisterNavigator): IRegisterNavigator

    @Binds
    abstract fun bindDaggerViewModelFactory(daggerViewModelFactory_Factory: DaggerViewModelFactory): ViewModelProvider.Factory
}


@Module
class RegisterModule {
    @Provides
    fun provideViewModelProvider(
        registerActivity: RegisterActivity,
        viewModelProviderFactory: ViewModelProvider.Factory
    ) =
        ViewModelProviders.of(registerActivity, viewModelProviderFactory)
}

@Subcomponent(
    modules = [
        RegisterBinderModule::class,
        RegisterModule::class
    ]
)
interface RegisterComponent : AndroidInjector<RegisterActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RegisterActivity>()
}