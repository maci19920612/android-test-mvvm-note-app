package team.maci.mvvmnoteapp.util

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object DateConverter{
    @JvmStatic
    private val defaultDateFormat = SimpleDateFormat("yyyy MMM dd", Locale.getDefault())

    @JvmStatic
    fun convert(date: Date?) : String{
        if(date == null){
            return ""
        }

        return defaultDateFormat.format(date)
    }
}