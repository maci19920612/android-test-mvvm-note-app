package team.maci.mvvmnoteapp.util

import org.joda.time.DateTime
import java.text.SimpleDateFormat
import java.util.*

object DateConverter{
    @JvmStatic
    private val defaultDateFormat = SimpleDateFormat("yyyy MMM dd", Locale.getDefault())

    @JvmStatic
    fun convert(date: DateTime?) : String{
        if(date == null){
            return ""
        }

        return date.toString("yyyy MMM dd")
    }
}