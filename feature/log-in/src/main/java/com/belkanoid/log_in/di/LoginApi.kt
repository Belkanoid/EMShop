package com.belkanoid.log_in.di

import com.belkanoid.di.BaseApi
import com.belkanoid.log_in.presentation.LoginFragment

interface LoginApi : BaseApi {

    fun inject(loginFragment: LoginFragment)

}