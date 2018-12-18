package team.maci.mvvmnoteapp

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Environment @Inject constructor(){

    fun isDebug() = BuildConfig.DEBUG

    fun baseApiUrl() = "http://10.0.2.2/note_app/"
}