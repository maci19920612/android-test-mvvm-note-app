package team.maci.mvvmnoteapp.database.converters

import androidx.room.TypeConverter
import java.util.*


class DateConveter{
    @TypeConverter
    fun convertToLong(date: Date?) : Long? = date?.time


    @TypeConverter()
    fun convertFromLong(longTime: Long?) = if(longTime == null) null else Date(longTime)
}