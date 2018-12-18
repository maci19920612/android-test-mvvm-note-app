package team.maci.mvvmnoteapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class DaggerViewModelFactory @Inject constructor(
    private val viewModelFactories : Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val factory = viewModelFactories[modelClass] ?: throw IllegalArgumentException("Not exists factory method for this viewModel: $modelClass")
        return factory.get() as T
    }

}