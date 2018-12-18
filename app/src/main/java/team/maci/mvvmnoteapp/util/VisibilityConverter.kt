package team.maci.mvvmnoteapp.util

import android.view.View

object VisibilityConverter{
    @JvmStatic
    fun gone(gone: Boolean) : Int{
        return if(gone) View.GONE else View.VISIBLE
    }

    @JvmStatic
    fun invisible(hidden: Boolean) : Int{
        return if(hidden) View.INVISIBLE else View.VISIBLE
    }
}