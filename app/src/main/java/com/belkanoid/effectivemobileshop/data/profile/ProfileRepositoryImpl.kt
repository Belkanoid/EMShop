package com.belkanoid.effectivemobileshop.data.profile

import com.belkanoid.local.LocalDataStore
import com.belkanoid.profile.domain.entity.UserInfo
import com.belkanoid.profile.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val localDataStore: LocalDataStore,
): ProfileRepository {
    override fun getUserInfo() = UserInfo(
        localDataStore.getUserName(),
        localDataStore.getUserSurname(),
        localDataStore.getUserPhone(),
        localDataStore.getFavoriteProductsId().size
    )

    override fun clearStorage() {
        localDataStore.clearStorage()
    }
}