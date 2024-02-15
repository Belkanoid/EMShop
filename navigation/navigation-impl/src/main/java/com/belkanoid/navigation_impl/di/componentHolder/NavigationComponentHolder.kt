package com.belkanoid.navigation_impl.di.componentHolder

import com.belkanoid.di.BaseComponentHolder
import com.belkanoid.navigation_impl.di.DaggerNavigationComponent

object NavigationComponentHolder : BaseComponentHolder<NavigationImplApi, NavigationDependencies>() {
    override fun build(dependencies: NavigationDependencies): NavigationImplApi =
        DaggerNavigationComponent.factory().create(dependencies)

}