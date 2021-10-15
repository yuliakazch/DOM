package com.dom.features.signin.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.dom.features.signin.data.api.SignInApi
import com.dom.features.signin.data.mapper.toDto
import com.dom.features.signin.data.mapper.toEntity
import com.dom.features.signin.domain.entity.Credentials
import com.dom.features.signin.domain.entity.Token
import com.dom.features.signin.domain.repository.SignInRepository
import com.dom.shared.util.KeyArgs.TOKEN
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val api: SignInApi,
    private val sharedPreferences: SharedPreferences
) : SignInRepository {

    override suspend fun signIn(credentials: Credentials) {
        clearToken()
        val credentialsDto = credentials.toDto()
        val token = api.signIn(credentialsDto.login, credentialsDto.passwordHash).toEntity()
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