package com.belkanoid.effectivemobileshop.data.login

import com.belkanoid.local.LocalDataStore
import com.belkanoid.log_in.domain.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val localDataStore: LocalDataStore
) : LoginRepository {
    override suspend fun saveUsername(name: String, surname: String, phone: String) {
        localDataStore.saveUserToLocalStore(name, surname, phone)
    }
}