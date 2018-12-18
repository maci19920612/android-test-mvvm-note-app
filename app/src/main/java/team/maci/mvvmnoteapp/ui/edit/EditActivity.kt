package team.maci.mvvmnoteapp.ui.edit

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import team.maci.mvvmnoteapp.R
import team.maci.mvvmnoteapp.databinding.ActivityEditBinding
import javax.inject.Inject

class EditActivity : FragmentActivity(){
    @Inject
    lateinit var viewModelProvider: ViewModelProvider

    @Inject
    lateinit var navigator: IEditNavigator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        val viewModel = viewModelProvider[EditViewModel::class.java]
        viewModel.navigator = navigator
        viewModel.onCreate(intent.getIntExtra(PARAM_NOTE_ID, -1))

        val binding = DataBindingUtil.setContentView<ActivityEditBinding>(this, R.layout.activity_edit)
        binding.viewModel = viewModel

    }

    companion object {
        const val PARAM_NOTE_ID = "Param.NoteId"
    }
}