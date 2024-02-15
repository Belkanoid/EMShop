package com.belkanoid.navigation_impl.di.componentHolder

import com.belkanoid.catalog.navigation.CatalogDirections
import com.belkanoid.di.BaseApi
import com.belkanoid.log_in.naviation.LoginDirections
import com.belkanoid.navigation_api.NavigationApi
import com.belkanoid.navigation_impl.presentation.NavigationFragment
import com.belkanoid.navigation_impl.presentation.TabsFragment
import com.belkanoid.profile.navigation.ProfileDirections

interface NavigationImplApi : BaseApi {

    fun inject(tabsFragment: TabsFragment)

    fun inject(navigationFragment: NavigationFragment)

    val profileNavigationApi: NavigationApi<ProfileDirections>

    val catalogNavigationApi: NavigationApi<CatalogDirections>

    val loginNavigationApi: NavigationApi<LoginDirections>
}