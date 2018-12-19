package team.maci.mvvmnoteapp.ui.edit

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
abstract class EditBinderModule {
    @Binds
    @IntoMap
    @ViewModelKey(EditViewModel::class)
    abstract fun bindViewModel(viewModel: EditViewModel): ViewModel

    @Binds
    abstract fun bindDaggerViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindNavigator(navigator: EditNavigator): IEditNavigator
}

@Module
class EditModule {
    @Provides
    fun provideViewModelProvider(
        activity: EditActivity,
        daggerViewModelFactory: DaggerViewModelFactory
    ): ViewModelProvider {
        return ViewModelProviders.of(activity, daggerViewModelFactory)
    }
}