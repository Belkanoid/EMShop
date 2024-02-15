package com.belkanoid.log_in.di

import com.belkanoid.di.BaseDependencies
import com.belkanoid.log_in.domain.LoginRepository
import com.belkanoid.log_in.naviation.LoginDirections
import com.belkanoid.navigation_api.NavigationApi

interface LoginDependencies : BaseDependencies {

    val loginRepository : LoginRepository

    val navigationApi : NavigationApi<LoginDirections>
}