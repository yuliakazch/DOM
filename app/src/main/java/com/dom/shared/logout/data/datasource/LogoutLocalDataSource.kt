package com.dom.shared.logout.data.datasource

interface LogoutLocalDataSource {

    fun clearToken()

    fun isTokenExist(): Boolean
}