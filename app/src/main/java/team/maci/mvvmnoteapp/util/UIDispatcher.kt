package team.maci.mvvmnoteapp.util

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlin.coroutines.CoroutineContext


class UIDispatcher : CoroutineDispatcher{
    private val handler: Handler
    constructor(handler: Handler = Handler(Looper.getMainLooper())){
        this.handler = handler
    }

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        handler.post {
            block.run()
        }
    }
}