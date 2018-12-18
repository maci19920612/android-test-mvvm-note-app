package team.maci.mvvmnoteapp.ui.list

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import team.maci.mvvmnoteapp.R
import team.maci.mvvmnoteapp.databinding.ActivityListBinding
import javax.inject.Inject

class ListActivity : FragmentActivity() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider

    @Inject
    lateinit var navigator: IListNavigator

    lateinit var viewModel : ListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        val dataBinding = DataBindingUtil.setContentView<ActivityListBinding>(this, R.layout.activity_list)

        viewModel = viewModelProvider.get(ListViewModel::class.java)
        viewModel.navigator = navigator
        dataBinding.viewModel = viewModel
    }


    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }
}