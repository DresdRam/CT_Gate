package com.mayv.ctgate.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    private const val PREFERENCE_NAME = "CT_GATE"

    private const val AUTH_TOKEN = "AUTH_TOKEN"
    private const val IP = "IP"
    private const val PORT = "PORT"
    private const val EXCLUDE_REMOVED = "EXCLUDE_REMOVED"
    private const val SEARCH_SIZE = "SEARCH_SIZE"


    fun getPreference(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val edit = edit()
        operation(edit)
        edit.apply()
    }

    var SharedPreferences.token
        get() = getString(AUTH_TOKEN, "")
        set(value) {
            edit {
                it.putString(AUTH_TOKEN, value)
            }
        }

    var SharedPreferences.ip
        get() = getString(IP, "192.168.1.60")
        set(value) {
            edit {
                it.putString(IP, value)
            }
        }

    var SharedPreferences.port
        get() = getString(PORT, "3026")
        set(value) {
            edit {
                it.putString(PORT, value)
            }
        }

    var SharedPreferences.excludeRemoved
        get() = getBoolean(EXCLUDE_REMOVED, false)
        set(value) {
            edit {
                it.putBoolean(EXCLUDE_REMOVED, value)
            }
        }

    var SharedPreferences.searchSize
        get() = getInt(SEARCH_SIZE, 20)
        set(value) {
            edit {
                it.putInt(SEARCH_SIZE, value)
            }
        }
}