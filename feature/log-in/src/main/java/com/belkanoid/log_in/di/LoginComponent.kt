package com.belkanoid.log_in.di

import dagger.Component

@Component(
    modules = [ViewModelModule::class],
    dependencies = [LoginDependencies::class]
)
interface LoginComponent: LoginApi {

    @Component.Factory
    interface Factory {
        fun create(
            dependencies: LoginDependencies
        ) : LoginComponent
    }

}