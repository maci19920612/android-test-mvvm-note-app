package team.maci.mvvmnoteapp.ui.details

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import team.maci.mvvmnoteapp.R
import team.maci.mvvmnoteapp.databinding.ActivityDetailsBinding
import javax.inject.Inject

class DetailsActivity : FragmentActivity() {
    @Inject
    lateinit var viewModelProvider: ViewModelProvider

    @Inject
    lateinit var navigator: IDetailsNavigator


    lateinit var viewModel: DetailsViewModel
    lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)


        viewModel = viewModelProvider[DetailsViewModel::class.java]
        viewModel.navigator = navigator

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
    }


    override fun onStart() {
        super.onStart()

        val noteId = intent.getIntExtra(PARAM_NOTE_ID, -1)

        if (noteId <= 0) {
            Toast.makeText(this, "Missing note id from parameter", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        viewModel.onStart(noteId)

        binding.viewModel = viewModel
    }

    companion object {
        const val PARAM_NOTE_ID = "Param.NoteId"
    }
}