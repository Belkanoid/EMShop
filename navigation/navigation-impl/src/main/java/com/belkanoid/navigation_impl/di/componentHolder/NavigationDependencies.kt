package com.belkanoid.navigation_impl.di.componentHolder

import com.belkanoid.di.BaseDependencies
import com.belkanoid.navigation_impl.NavigationActivityProvider
import com.belkanoid.navigation_impl.domain.NavigationUseCase

interface NavigationDependencies: BaseDependencies {

    val navigationActivityProvider : NavigationActivityProvider

    val navigationUseCase : NavigationUseCase

}