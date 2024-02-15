package com.belkanoid.navigation_impl.directionsImpl

import com.belkanoid.di.annotations.SetTabsAction
import com.belkanoid.log_in.naviation.LoginDirections
import com.belkanoid.navigation_api.NavigationApi
import javax.inject.Inject

class LoginNavigationImpl @Inject constructor(
    @SetTabsAction private val openTabsFragment: () -> Unit,
) : NavigationApi<LoginDirections> {
    override fun navigate(direction: LoginDirections) = when (direction) {
        LoginDirections.ToTabs -> {
            openTabsFragment()
        }
    }
}