package com.belkanoid.log_in.di

import com.belkanoid.di.BaseComponentHolder

object LoginComponentHolder : BaseComponentHolder<LoginApi, LoginDependencies>() {
    override fun build(dependencies: LoginDependencies): LoginApi =
        DaggerLoginComponent.factory().create(dependencies)
}