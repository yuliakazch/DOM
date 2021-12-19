package com.dom.shared.signin.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.dom.shared.signin.data.mapper.toDto
import com.dom.shared.signin.data.mapper.toEntity
import com.dom.shared.signin.domain.entity.Credentials
import com.dom.shared.signin.domain.entity.Token
import com.dom.shared.signin.domain.repository.SignInRepository
import com.dom.core.KeyArgs.TOKEN
import com.dom.shared.signin.data.datasource.SignInDataSource
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val dataSource: SignInDataSource,
    private val sharedPreferences: SharedPreferences
) : SignInRepository {

    override suspend fun signIn(credentials: Credentials) {
        clearToken()
        val credentialsDto = credentials.toDto()
        val token = dataSource.signIn(credentialsDto).toEntity()
        saveToken(token)
        return
    }

    private fun clearToken() {
        if (isTokenExist()) {
            sharedPreferences.edit(commit = true) {
                remove(TOKEN)
            }
        }
    }

    private fun isTokenExist(): Boolean =
        !sharedPreferences.getString(TOKEN, null).isNullOrEmpty()

    private fun saveToken(token: Token) {
        sharedPreferences.edit(commit = true) {
            putString(TOKEN, token.toDto().value)
        }
    }
}