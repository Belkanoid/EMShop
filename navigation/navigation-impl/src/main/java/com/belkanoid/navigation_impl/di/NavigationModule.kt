package com.belkanoid.navigation_impl.di

import androidx.navigation.NavController
import com.belkanoid.catalog.navigation.CatalogDirections
import com.belkanoid.di.annotations.SetLoginAction
import com.belkanoid.di.annotations.SetTabsAction
import com.belkanoid.log_in.naviation.LoginDirections
import com.belkanoid.navigation_api.NavigationApi
import com.belkanoid.navigation_impl.NavigationActivityProvider
import com.belkanoid.navigation_impl.directionsImpl.CatalogNavigationImpl
import com.belkanoid.navigation_impl.directionsImpl.LoginNavigationImpl
import com.belkanoid.navigation_impl.directionsImpl.ProfileNavigationImpl
import com.belkanoid.profile.navigation.ProfileDirections
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [NavigationModule.Binder::class]
)
class NavigationModule {

    @Provides
    fun provideNavController(
        activityProvider: NavigationActivityProvider
    ): NavController = activityProvider.get()
        ?.getNavigationTabsFragment()
        ?.navController
        ?: error("Do not make navigation calls while activity is not available")


    @[Provides SetTabsAction]
    fun provideOpenTabsFragmentAction(
        activityProvider: NavigationActivityProvider
    ): () -> Unit = activityProvider.get()?.setTabsFragment()
        ?: error("Do not make navigation calls while activity is not available")

    @[Provides SetLoginAction]
    fun provideOpenLoginFragmentAction(
        activityProvider: NavigationActivityProvider
    ): () -> Unit = activityProvider.get()?.setLoginFragment()
        ?: error("Do not make navigation calls while activity is not available")

    @Module
    interface Binder {

        @Binds
        fun bindLoginNavigation(impl: LoginNavigationImpl): NavigationApi<LoginDirections>

        @Binds
        fun bindProfileNavigation(impl: ProfileNavigationImpl): NavigationApi<ProfileDirections>

        @Binds
        fun bindCatalogNavigation(impl: CatalogNavigationImpl): NavigationApi<CatalogDirections>

    }
}