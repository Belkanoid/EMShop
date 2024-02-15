package com.belkanoid.log_in.domain

interface LoginRepository {

    suspend fun saveUsername(name: String, surname: String, phone: String)

}