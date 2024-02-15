package com.belkanoid.effectivemobileshop.di

import com.belkanoid.catalog.di.componentHolder.CatalogComponentHolder
import com.belkanoid.catalog.di.componentHolder.CatalogDependencies
import com.belkanoid.log_in.di.LoginComponentHolder
import com.belkanoid.log_in.di.LoginDependencies
import com.belkanoid.navigation_impl.di.componentHolder.NavigationComponentHolder
import com.belkanoid.navigation_impl.di.componentHolder.NavigationDependencies
import com.belkanoid.profile.di.componentHolder.ProfileComponentHolder
import com.belkanoid.profile.di.componentHolder.ProfileDependencies
import javax.inject.Inject
import javax.inject.Provider

class ComponentHolderInitializer @Inject constructor(
    private val navigationDependencies: Provider<NavigationDependencies>,
    private val profileDependencies: Provider<ProfileDependencies>,
    private val catalogDependencies: Provider<CatalogDependencies>,
    private val loginDependencies: Provider<LoginDependencies>,
) {

    fun init() {
        NavigationComponentHolder.init(navigationDependencies)
        ProfileComponentHolder.init(profileDependencies)
        CatalogComponentHolder.init(catalogDependencies)
        LoginComponentHolder.init(loginDependencies)
    }

}