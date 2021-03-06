package com.dom.shared.logout.data.datasource

import android.content.SharedPreferences
import androidx.core.content.edit
import com.dom.core.KeyArgs
import javax.inject.Inject

class LogoutLocalDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LogoutLocalDataSource {

    override fun clearToken() {
        sharedPreferences.edit(commit = true) {
            remove(KeyArgs.TOKEN)
        }
    }

    override fun isTokenExist(): Boolean =
        !sharedPreferences.getString(KeyArgs.TOKEN, null).isNullOrEmpty()
}