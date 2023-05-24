package com.example.homework5.data.remote

import android.content.Context
import androidx.core.content.edit
import com.example.homework5.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefStorage @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private val prefs by lazy{
        context.getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE)
    }

    var token: String?
        get() = prefs.getString(Constants.TOKEN_KEY, null)
        set(value){
            prefs.edit {
                putString(Constants.TOKEN_KEY, value)
            }
        }

    var userId: String?
        get() = prefs.getString(Constants.USER_ID_KEY, null)
        set(value){
            prefs.edit{
                putString(Constants.USER_ID_KEY, value)
            }
        }
}