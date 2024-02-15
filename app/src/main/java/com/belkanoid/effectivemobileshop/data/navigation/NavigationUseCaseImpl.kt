package com.belkanoid.effectivemobileshop.data.navigation

import com.belkanoid.local.LocalDataStore
import com.belkanoid.navigation_impl.domain.NavigationUseCase
import javax.inject.Inject

class NavigationUseCaseImpl @Inject constructor(
    private val localDataStore: LocalDataStore
) : NavigationUseCase {
    override fun getIsUserLoggedIn() = localDataStore.isUserStored()
}