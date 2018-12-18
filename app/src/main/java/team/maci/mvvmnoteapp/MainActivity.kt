package team.maci.mvvmnoteapp

import android.app.Activity
import android.os.Bundle
import dagger.android.AndroidInjection
import kotlinx.coroutines.*
import team.maci.mvvmnoteapp.di.AppComponent
import team.maci.mvvmnoteapp.di.ComponentContainer
import team.maci.mvvmnoteapp.util.UIDispatcher
import timber.log.Timber
import javax.inject.Inject


class MainActivity : Activity() {

    @Inject
    lateinit var uiDispatcher: UIDispatcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)

        Timber.d("Before blocking operation")
        runBlocking {
            delay(1000)
        }

        Timber.d("After blocking operation")
        GlobalScope.launch {
            delay(1000)
            Timber.d("Example")
        }

        GlobalScope.launch(uiDispatcher) {
            val result = GlobalScope.async {
                example()
            }.await()
            Timber.d("Suspend function result: %d", result)
            Timber.d("Current thread in async function: %s", Thread.currentThread())
        }
        Timber.d("Current thread in main thread: %s", Thread.currentThread())
    }

    suspend fun example(): Int {
        Timber.d("Current thread in suspend function: %s", Thread.currentThread())
        val deferred = GlobalScope.async async@{
            return@async 4
        }
        return deferred.await()
    }
}
