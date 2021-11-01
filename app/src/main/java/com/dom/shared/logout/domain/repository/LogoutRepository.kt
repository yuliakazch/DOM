package com.dom.shared.logout.domain.repository

interface LogoutRepository {

    fun clearToken()

    fun isTokenExist(): Boolean
}