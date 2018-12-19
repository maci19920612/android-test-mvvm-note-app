package team.maci.mvvmnoteapp.database.converters

import androidx.room.TypeConverter
import org.joda.time.DateTime
import java.util.*


class DateConveter{
    @TypeConverter
    fun convertToLong(date: DateTime?) : Long? = date?.millis


    @TypeConverter()
    fun convertFromLong(longTime: Long?) = if(longTime == null) null else DateTime(longTime)
}