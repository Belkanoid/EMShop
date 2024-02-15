package com.belkanoid.navigation_impl.di

import com.belkanoid.navigation_impl.di.componentHolder.NavigationDependencies
import com.belkanoid.navigation_impl.di.componentHolder.NavigationImplApi
import dagger.Component

@Component(
    modules = [NavigationModule::class],
    dependencies = [NavigationDependencies::class]
)
interface NavigationComponent : NavigationImplApi {

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: NavigationDependencies
        ): NavigationComponent
    }

}