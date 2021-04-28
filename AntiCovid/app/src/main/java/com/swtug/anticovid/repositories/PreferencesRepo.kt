package com.swtug.anticovid.repositories

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.swtug.anticovid.models.User
import com.swtug.anticovid.models.Vaccination

object PreferencesRepo {
    private const val PREFERENCES_NAME = "ANTI_COVID_APP"
    private const val VACCINATION = "VACCINATION"
    private val TERMS_OF_USE = "TERMS_OF_USE_ACCEPTED"
    private const val LOGGED_IN_USER = "LOGGED_IN_USER"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun saveAcceptTermsOfUse(context: Context, accept: Boolean) {
        getPreferences(context)
            .edit()
            .putBoolean(TERMS_OF_USE, accept)
            .apply()
    }

    fun getTermsOfUseAccepted(context: Context) : Boolean {
        return getPreferences(context).getBoolean(TERMS_OF_USE, false)
    }

    fun saveVaccination(context: Context, vaccination: Vaccination) {
        val gson = Gson()
        val json = gson.toJson(vaccination)

        getPreferences(context)
            .edit()
            .putString(VACCINATION, json)
            .apply()
    }

    fun getVaccination(context: Context): Vaccination? {
        val json =  getPreferences(context)
            .getString(VACCINATION, null)

        return if(json.isNullOrEmpty()) {
            null
        } else {
            val gson = Gson()
            gson.fromJson(json, Vaccination::class.java)
        }
    }
    fun saveUser(context: Context, user: User) {
        val gson = Gson()
        val json = gson.toJson(user)

        getPreferences(context)
            .edit()
            .putString(LOGGED_IN_USER, json)
            .apply()
    }

    fun getUser(context: Context): User? {
        val json =  getPreferences(context)
            .getString(LOGGED_IN_USER, null)

        return if(json.isNullOrEmpty()) {
            null
        } else {
            val gson = Gson()
            gson.fromJson(json, User::class.java)
        }
    }
    fun deleteUser(context: Context){
        getPreferences(context).edit().remove(LOGGED_IN_USER).apply()
    }

}