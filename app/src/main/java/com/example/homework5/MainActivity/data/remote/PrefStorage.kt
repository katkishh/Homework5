package com.example.homework5.MainActivity.data.remote

import android.content.Context
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefStorage @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    companion object{
        private const val PREFS_NAME = "Prefs"
        private const val TOKEN_KEY = "Auth"
    }

    private val prefs by lazy{
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    var token: String?
        get() = prefs.getString(TOKEN_KEY, null)
        set(value){
           // prefs.edit().apply {
             //   putString(TOKEN_KEY, value)
               // apply()
           // }
            prefs.edit {            //то же, что и сверху
                putString(TOKEN_KEY, value)
            }
        }
}