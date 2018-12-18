package team.maci.mvvmnoteapp.ui.details

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

@Module
abstract class DetailsBinderModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindViewModel(detailsViewModel: DetailsViewModel): ViewModel

    @Binds
    abstract fun bindDaggerViewModelFactory(daggerViewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindDetailsNavigator(detailsNavigator: DetailsNavaigator): IDetailsNavigator
}

@Module
class DetailsModule {
    @Provides
    fun provideViewModelProvider(
        activity: DetailsActivity,
        daggerViewModelFactory: DaggerViewModelFactory
    ): ViewModelProvider {
        return ViewModelProviders.of(activity, daggerViewModelFactory)
    }
}

@Subcomponent(
    modules = [
        DetailsBinderModule::class,
        DetailsModule::class
    ]
)
interface DetailsComponent : AndroidInjector<DetailsActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DetailsActivity>()
}