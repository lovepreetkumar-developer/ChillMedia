package com.techwin.githubexamples.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.service.autofill.UserData
import androidx.annotation.RequiresApi
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.techwin.githubexamples.util.Constants

class PreferenceProvider(context: Context) {

    private val appContext = context.applicationContext

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun clear(): Boolean {
        return preferences.edit().clear().commit()
    }

    fun setUser(signUpResponseData: UserData): Boolean {
        return preferences.edit().putString(
            Constants.USER, Gson().toJson(signUpResponseData)
        ).commit()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun getUser(): UserData? {
        return try {
            val s: String = preferences.getString(Constants.USER, null)
                ?: return null
            Gson().fromJson(s, UserData::class.java)
        } catch (e: Exception) {
            null
        }
    }

    /*fun setThoughtsList(thoughtBeanMain: ArrayList<ThoughtModel>) {
        preferences.edit().putString(
                Constants.THOUGHTS_LIST,
                Gson().toJson(thoughtBeanMain)
        ).apply()
    }

    fun getThoughtsList(): ArrayList<ThoughtModel?>? {
        return try {
            val s: String = preferences.getString(Constants.THOUGHTS_LIST, null) ?: return null
            val type = object : TypeToken<List<ThoughtModel?>?>() {}.type
            Gson().fromJson<ArrayList<ThoughtModel?>>(s, type)
        } catch (e: Exception) {
            null
        }
    }*/

    fun setShowLockScreen(value: Boolean): Boolean {
        return preferences.edit().putBoolean(
            Constants.SHOW_LOCK_SCREEN,
            value
        ).commit()
    }

    fun getShowLockScreen(): Boolean {
        return preferences.getBoolean(Constants.SHOW_LOCK_SCREEN, true)
    }

    fun setTimezone(timezone: String?) {
        preferences.edit().putString(
            Constants.TIMEZONE,
            timezone
        ).apply()
    }

    fun getTimezone(): String? {
        return preferences.getString(Constants.TIMEZONE, "")
    }

    fun setUserPicture(timezone: String?) {
        preferences.edit().putString(
            Constants.TIMEZONE,
            timezone
        ).apply()
    }

    fun getUserPicture(): String? {
        return preferences.getString(Constants.TIMEZONE, "")
    }

    fun setLatitude(latitude: String?) {
        preferences.edit().putString(
            Constants.LATITUDE,
            latitude
        ).apply()
    }

    fun getLatitude(): String? {
        return preferences.getString(Constants.LATITUDE, "")
    }

    fun setLongitude(longitude: String?) {
        preferences.edit().putString(
            Constants.LONGITUDE,
            longitude
        ).apply()
    }

    fun getLongitude(): String? {
        return preferences.getString(Constants.LONGITUDE, "")
    }

    fun setNotification(value: Boolean): Boolean {
        return preferences.edit().putBoolean(
            Constants.NOTIFICATION,
            value
        ).commit()
    }

    fun getNotification(): Boolean {
        return preferences.getBoolean(Constants.NOTIFICATION, false)
    }

    fun setRemember(): Boolean {
        return preferences.edit().putBoolean(
            Constants.REMEMBER,
            true
        ).commit()
    }

    fun getRemember(): Boolean {
        return preferences.getBoolean(Constants.REMEMBER, false)
    }

    fun setFirebaseToken(token: String?): Boolean {
        return preferences.edit().putString(
            Constants.FIREBASE_TOKEN,
            token
        ).commit()
    }

    fun setLanguage(language: String?): Boolean {
        return preferences.edit().putString(
            Constants.LANGUAGE,
            language
        ).commit()
    }

    fun getLanguage(): String? {
        return preferences.getString(Constants.LANGUAGE, null)
    }

    fun setCurrentDate(currentDate: String?): Boolean {
        return preferences.edit().putString(
            Constants.CURRENT_DATE,
            currentDate
        ).commit()
    }

    fun getCurrentDate(): String? {
        return preferences.getString(Constants.CURRENT_DATE, null)
    }
}