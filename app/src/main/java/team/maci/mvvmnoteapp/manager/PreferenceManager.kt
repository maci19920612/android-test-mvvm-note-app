package team.maci.mvvmnoteapp.manager

import android.content.SharedPreferences
import android.preference.PreferenceManager
import javax.inject.Inject

class PreferenceManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
){

    fun hasToken() : Boolean = this.sharedPreferences.getString(PREF_TOKEN, null) != null

    fun getToken() = this.sharedPreferences.getString(PREF_TOKEN, null)

    fun setToken(token: String) = set(PREF_TOKEN, token)


    private fun set(key: String, value: String){
        sharedPreferences.edit()
            .putString(key, value)
            .apply()
    }

    companion object {
        private const val PREF_TOKEN = "pref.token"
    }
}