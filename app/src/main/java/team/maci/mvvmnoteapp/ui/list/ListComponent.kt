package team.maci.mvvmnoteapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.*
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import team.maci.mvvmnoteapp.util.DaggerViewModelFactory
import team.maci.mvvmnoteapp.util.ViewModelKey

@Module
abstract class ListBinderModule {
    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(model: ListViewModel) : ViewModel

    @Binds
    abstract fun bindsNavigator(navigator: ListNavigator) : IListNavigator

    @Binds
    abstract fun bindsDaggerViewModelProvider(daggerViewModelFactory: DaggerViewModelFactory) : ViewModelProvider.Factory
}

@Module
class ListModule {
    @Provides
    fun provideViewModelProvider(
        daggerViewModelFactory: DaggerViewModelFactory,
        listActivity: ListActivity
    ) : ViewModelProvider = ViewModelProviders.of(listActivity,daggerViewModelFactory)
}

@Subcomponent(
    modules = [
        ListBinderModule::class,
        ListModule::class
    ]
)
interface ListComponent : AndroidInjector<ListActivity>{

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ListActivity>()
}